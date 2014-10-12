package com.myCommunity.ui.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.myCommunity.biz.MessageReceivingBiz;
import com.myCommunity.biz.MessageSendingBiz;
import com.myCommunity.entity.MessageReceiving;
import com.myCommunity.entity.MessageSending;
import com.myCommunity.entity.User;
import com.myCommunity.ui.util.ContextUtils;

@Controller
@RequestMapping("/message")
public class MessageController {
	
	@Resource
	private MessageReceivingBiz messageReceivingBiz;
	
	@Resource
	private MessageSendingBiz messageSendingBiz;
	
	@RequestMapping("/receive_list")
	public String receiveList(HttpServletRequest request, Map<String,Object> model){
		User loginUser = (User)request.getSession().getAttribute("loginUser");
		model.put("messages", messageReceivingBiz.getMessageReceivingsByReceiverId(loginUser.getId(), MessageReceiving.STATUS_NORMAL));
		return "message_receive_list";
	}
	
	@RequestMapping("/receive_unreaded")
	public String receiveUnReaded(HttpServletRequest request, Map<String,Object> model){
		User loginUser = (User)request.getSession().getAttribute("loginUser");
		model.put("messages", messageReceivingBiz.getUnReadedMessageReceivingsByReceiverId(loginUser.getId()));
		return "message_receive_unreaded";
	}
	
	@RequestMapping("/receive_recycle")
	public String receiveRecycle(HttpServletRequest request, Map<String,Object> model){
		User loginUser = (User)request.getSession().getAttribute("loginUser");
		model.put("messages", messageReceivingBiz.getMessageReceivingsByReceiverId(loginUser.getId(), MessageReceiving.STATUS_RECYCLE));
		return "message_receive_recycle";
	}
	
	@RequestMapping("/message_menu")
	public String menu(){
		return "message_menu";
	}
	
	@RequestMapping("/receive_mark_recycle")
	public String receiveMarkRecycle(int id){
		messageReceivingBiz.markRecycle(id);
		return "redirect:receive_list";
	}
	
	
	@RequestMapping("/receive_mark_delete")
	public String receiveMarkDelete(int id){
		messageReceivingBiz.markDelete(id);
		return "redirect:receive_recycle";
	}
	
	@RequestMapping("/receive_mark_normal")
	public String receiveMarkNormal(int id){
		messageReceivingBiz.markNormal(id);
		return "redirect:receive_recycle";
	}
	
	@RequestMapping("/receive_detail")
	public String receiveDetail(int id, Map<String,Object> model){
		model.put("msg", messageReceivingBiz.read(id));
		return "message_receive_detail";
	}
	
	@RequestMapping("/send_list")
	public String sendList(HttpServletRequest request, Map<String, Object> model){
		User loginUser = (User)request.getSession().getAttribute("loginUser");
		model.put("messages", messageSendingBiz.getMessageSendingsBySenderId(loginUser.getId(), MessageSending.STATUS_SENDED));
		return "message_send_list";
	}
	
	@RequestMapping("/send_recycle")
	public String sendRecycle(HttpServletRequest request, Map<String, Object> model){
		User loginUser = (User)request.getSession().getAttribute("loginUser");
		model.put("messages", messageSendingBiz.getMessageSendingsBySenderId(loginUser.getId(), MessageSending.STATUS_RECYCLE));
		return "message_send_recycle";
	}
	
	@RequestMapping("/send_draft")
	public String sendDraft(HttpServletRequest request, Map<String, Object> model){
		User loginUser = (User)request.getSession().getAttribute("loginUser");
		model.put("messages", messageSendingBiz.getMessageSendingsBySenderId(loginUser.getId(), MessageSending.STATUS_DRAFT));
		return "message_send_draft";
	}
	
	@RequestMapping("/send_mark_recycle")
	public String sendMarkRecycle(int id){
		messageSendingBiz.markRecycle(id);
		return "redirect:send_list";
	}
	
	@RequestMapping("/send_mark_delete")
	public String snedMarkDelete(int id){
		messageSendingBiz.markDelete(id);
		return "redirect:send_recycle";
	}
	
	@RequestMapping("/send_mark_sended")
	public String snedMarkSended(int id){
		messageSendingBiz.markSended(id);
		return "redirect:send_recycle";
	}
	
	@RequestMapping("/send_really_delete")
	public String sendReallyDelete(int id){
		messageSendingBiz.delete(id);
		return "redirect:send_draft";
	}
	
	@RequestMapping(value="/create")
	public String create(@RequestParam(value="id",required=false,defaultValue="0") int id,
						   @RequestParam(value="readonly",required=false,defaultValue="0")int readonly,
						   String receiver,
						   Map<String,Object> model){
		model.put("readonly", readonly);
		model.put("receiver", receiver);
		if(id>0)
			model.put("message", messageSendingBiz.fetchById(id));
		return "message_create";
	}
	
	@RequestMapping(value="/save")
	public String save(HttpServletRequest request, 
			@RequestParam(value="id",required=false,defaultValue="0") int id, 
			String title, String receivers, String content, int status){
		User loginUser = (User)request.getSession().getAttribute("loginUser");
		MessageSending ms = new MessageSending(
				0, title, content, receivers,
				new Timestamp(Calendar.getInstance().getTimeInMillis()), 
				loginUser, status);
		if(id>0){	//id大于0则为修改
			ms.setId(id);
			messageSendingBiz.update(ms);
		}else{
			messageSendingBiz.add(ms);	
		}
		if(status == MessageSending.STATUS_SENDED){	//状态若为“发送”则添加收件方信息，若为“草稿”则不添加
			Integer[] ids = getReceiversIdsByNames(receivers);
			for(int uid : ids){
				MessageReceiving mr = new MessageReceiving(0, ms, new User(uid), false, MessageReceiving.STATUS_NORMAL);
				messageReceivingBiz.add(mr);
			}
			return "redirect:send_list";
		}else{
			return "redirect:send_draft";
		}
		
	}
	
	private Integer[] getReceiversIdsByNames(String receivers){
		try {
			List<Integer> list = new ArrayList<Integer>();
			String[] names = receivers.split(",");
			for(String name : names){
				if(!ContextUtils.getUsersMap().containsKey(name))
					return null;
				list.add(ContextUtils.getUsersMap().get(name));
			}
			return list.toArray(new Integer[]{});
		} catch (Exception e) {
			return null;
		}
	}
	
	@RequestMapping("/checkReceiversAvalable")
	@ResponseBody
	public String checkReceiversAvalable(String receivers){
		Integer[] ids = getReceiversIdsByNames(receivers);
		boolean available = (ids!=null&&ids.length>0);
		return available+"";
	}
	
}
