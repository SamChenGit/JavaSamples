<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
		<div id="message-menu" class="col-md-2 panel panel-default">
			<ul>
				<li>
					<a>收件箱</a>
					<ul>
						<li><a href="message/receive_unreaded">未读邮件</a></li>
						<li><a href="message/receive_list">所有邮件</a></li>
						<li><a href="message/receive_recycle">回收站</a></li>
					</ul>
				</li>
				<li>
					<a>发件箱</a>
					<ul>
						<li><a href="message/send_draft">草稿箱</a></li>
						<li><a href="message/send_list">已发送</a></li>
						<li><a href="message/send_recycle">回收站</a></li>
					</ul>
				</li>
				<li><a href="message/create">编写新邮件</a></li>
			</ul>
		</div>	