package com.myCommunity.ui.controller;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.myCommunity.biz.BizException;
import com.myCommunity.biz.CommentBiz;
import com.myCommunity.biz.ForumBiz;
import com.myCommunity.biz.TopicBiz;
import com.myCommunity.biz.UserBiz;
import com.myCommunity.entity.Comment;
import com.myCommunity.entity.Forum;
import com.myCommunity.entity.Topic;
import com.myCommunity.entity.User;
import com.myCommunity.ui.util.ContextUtils;

@Controller
public class HomeController {
	
	@Resource
	private ForumBiz forumBiz;
	@Resource
	private TopicBiz topicBiz;
	@Resource
	private CommentBiz commentBiz;
	@Resource
	private UserBiz userBiz;
	
	@RequestMapping(value={"/","/index"})
	public String index(Map<String,Object> model){
		UserBiz userBiz = (UserBiz)ContextUtils.getBeanFactory().getBean("userBiz");
		System.out.println("---------------"+userBiz);
		model.put("forums", forumBiz.getForumsWith2LevelRecursive());
		model.put("topics", topicBiz.getLatestUpdatedTopics(6));
		return "index";
	}
	
	@RequestMapping("/forum/{id}")
	public String forum(@PathVariable int id, 
						@RequestParam(value="page",required=false,defaultValue="1") int pageNum, 
						Map<String,Object> model){
		final int PAGE_SIZE = 5;
		model.put("forum", forumBiz.fetchById(id));
		model.put("bestTopics", topicBiz.getBestTopicsByForumId(id));
		model.put("normalTopics", topicBiz.getNormalTopicsByForumId(id, PAGE_SIZE, pageNum));
		model.put("totalRows", topicBiz.fetchNormalTopicsRowsByForumId(id));
		return "forum";
	}
	
	@RequestMapping(value="/topic/{id}",method=RequestMethod.GET)
	public String topic(@PathVariable int id, 
			@RequestParam(value="page",required=false,defaultValue="1") int pageNum, 
			Map<String,Object> model){
		final int PAGE_SIZE = 5;
		model.put("topic", topicBiz.readTheTopic(id));
		model.put("comments", commentBiz.getCommentsByTopicId(id, PAGE_SIZE, pageNum));
		model.put("totalRows", commentBiz.fetchCommentsRowsByTopicId(id)+1);
		model.put("pageSize", PAGE_SIZE);
		return "topic";
	}
	
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public String login(){
		return "login";
	}
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public String login(String username, String password, HttpServletRequest request, Map<String,Object> model){
		User loginUser = userBiz.checkLogin(username, password);
		if(loginUser != null){
			request.getSession().setAttribute("loginUser", loginUser);
			return "redirect:index";
		}else{
			model.put("username", username);
			model.put("errorMessage", "用户名或密码有误");
			return "login";
		}
	}
	
	@RequestMapping("logout")
	public String logout(HttpServletRequest request){
		request.getSession().removeAttribute("loginUser");
		return "redirect:index";
	}
	
	@RequestMapping(value="register",method=RequestMethod.GET)
	public String register(){
		return "register";
	}
	
	@RequestMapping(value="register",method=RequestMethod.POST)
	public String register(User user, Map<String,Object> model){
		try {
			userBiz.register(user);
			return "redirect:login";	
		} catch (BizException e) {
			model.put("errorMessage", e.getMessage());
			model.put("user", user);
			return "register";
		}
	}
	
	@RequestMapping(value="/personal",method=RequestMethod.GET)
	public String personal(Map<String,Object> model){
		return "personal";
	}
	
	@RequestMapping(value="/personal",method=RequestMethod.POST)
	public String personal(User user,
			HttpServletRequest request,
			@RequestParam(value="headImage", required=false) MultipartFile headImage,
			Map<String,Object> model) throws IOException{
		User updateUser = userBiz.fetchById(user.getId());
		updateUser.setPassword(user.getPassword());
		updateUser.setNickname(user.getNickname());
		updateUser.setSignature(user.getSignature());
		userBiz.update(updateUser);
		request.getSession().setAttribute("loginUser", updateUser);
		if(!headImage.isEmpty()){
			saveImage(updateUser.getId()+".jpg", headImage, request);
		}
		return "redirect:personal";
	}
	
	private void saveImage(String filename, MultipartFile image, HttpServletRequest request) throws IOException {
		ServletContext ctx = request.getSession().getServletContext();
		File dest = new File(ctx.getRealPath("/Content/pictures/user") + "/" + filename);
		FileUtils.writeByteArrayToFile(dest, image.getBytes());
	}
	
	@RequestMapping("/checkUsernameAvalable")
	@ResponseBody
	public String checkUsernameAvalable(String username){
		boolean available = userBiz.checkUsernameAvailable(username);
		return available+"";
	}
	
	@RequestMapping(value="/createComment",method=RequestMethod.POST)
	public String createComment(Comment comment, HttpServletRequest request){
		User user = (User)request.getSession().getAttribute("loginUser");
		comment.setCommenter(user);
		comment.setCommentType(1);	//1为对帖子topic作评论，其它值暂无使用
		comment.setCommentTime(new Timestamp(new Date().getTime()));	//当前时间
		comment.setIp(request.getRemoteAddr());
		comment.setStatus(true);
		commentBiz.add(comment);
		return "redirect:topic/"+comment.getReference().getId();
	}
	
	@RequestMapping(value="/deleteComment", method=RequestMethod.POST)
	public String deleteComment(int id, HttpServletRequest request){
		Comment comment = commentBiz.fetchById(id);
		int topicId = comment.getReference().getId();
		commentBiz.delete(id);
		return "redirect:topic/"+topicId;
	}
	@RequestMapping(value="/create_topic",method=RequestMethod.GET)
	public String createTopic(
			@RequestParam(value="forumId",defaultValue="0",required=true) int forumId, 
			Map<String,Object> model){
		List<Forum> forums = forumBiz.getForumsWith2LevelRecursive();
		model.put("forums", forums);
		return "create_topic";
	}
	
	@RequestMapping(value="/create_topic",method=RequestMethod.POST)
	public String createTopic(Topic topic, HttpServletRequest request, Map<String,Object> model){
		User loginUser = (User)request.getSession().getAttribute("loginUser");
		topic.setAuthor(loginUser);
		topic.setBest(false);
		topic.setClicks(0);
		topic.setCommentTimes(0);
		topic.setCreateTime(new Timestamp(Calendar.getInstance().getTimeInMillis()));
		topic.setUpdateTime(topic.getCreateTime());
		topic.setIp(request.getRemoteAddr());
		topic.setStatus(true);
		topicBiz.add(topic);
		return "redirect: topic/"+topic.getId();
	}
	
	@RequestMapping(value="/search_result",method=RequestMethod.GET)
	public String searchResult(String title,
			@RequestParam(value="page",required=false,defaultValue="1") int pageNum, 
			Map<String,Object> model){
		final int pageSize = 5;
		model.put("topics", topicBiz.getTopicsByTitle(title, pageSize, pageNum));
		model.put("totalRows", topicBiz.fetchTopicsRowsByTitle(title));
		return "search_result";
	}
}
