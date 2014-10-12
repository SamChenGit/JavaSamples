drop table CommunityMessageReceiving;
drop table CommunityMessageSending;
drop table CommunityComment;
drop table CommunityTopic;
drop table CommunityForumMaster;
drop table CommunityForum;
drop table CommunityUser;
drop table CommunityRole;
/
drop sequence seq_community_role;
drop sequence seq_community_user;
drop sequence seq_community_forum;
drop sequence seq_community_forum_master;
drop sequence seq_community_topic;
drop sequence seq_community_comment;
drop sequence seq_community_message_sending;
drop sequence seq_community_message_recei;
/

create table CommunityRole(
       Id    number(3) primary key,
       Name  varchar2(50) not null unique      
)
/
insert into CommunityRole values(1,'普通用户');
insert into CommunityRole values(2,'版主');
insert into CommunityRole values(3,'管理员');
commit;
/
create sequence seq_community_role start with 4 increment by 1;
select * from CommunityRole;
/

create table CommunityUser(
       Id    number(10) primary key,                      
       Username varchar2(50) not null unique,         
       Nickname varchar2(50) not null,       
       Password varchar2(50) not null,
       Signature varchar(256),
       Email varchar2(256) not null,
       Grade number(10) null,           
       RoleId number(3) not null,
       Status number(1) not null 
);
alter table CommunityUser add constraints fk_Community_User_Role foreign key(RoleId) references CommunityRole(Id);
/
insert into CommunityUser values(1,'sam','唐三爷','123','唐僧洗头爱飘柔','tang3@126.com',10001,1,1);
insert into CommunityUser values(2,'jack','李四叔','123','犯我大叔，虽远必诛...','li4@126.com',1001,1,1);
insert into CommunityUser values(3,'tom','爱说话的tomcat','123','你干嘛学我说话...','wang5@126.com',101,1,1);
insert into CommunityUser values(4,'candy','糖果小M','123','妹纸也是手机控','candy5@126.com',101,1,1);
insert into CommunityUser values(5,'lily','莉莉丝','123','银河系立的小白','lily5@126.com',101,1,1);
insert into CommunityUser values(6,'jammy','几米阳光','123','向左向右......稍息立正','jammy@126.com',101,1,1);
insert into CommunityUser values(7,'rose','铿锵玫瑰','123','黑玫瑰与白玫瑰','rose@126.com',101,1,1);
insert into CommunityUser values(8,'admin','大管理员','123','给我三千城管，还你世界和平','zhao6@126.com',10001,3,1);
commit;
create sequence seq_community_user start with 9 increment by 1;
select * from CommunityUser;
/

create table CommunityForum(
  Id number(3) not null primary key,
  Name varchar2(20) not null unique,
  Description varchar2(500) not null,
  ParentId number(3) null
);
insert into CommunityForum values(1,'智能手机OS讨论区','各个手机操作系统的最新资讯,公共资源',null);
insert into CommunityForum values(2,'公共资源分享区','分享手机公共资源：电影、音乐、电子书、漫画等通用的资源',null);
insert into CommunityForum values(3,'Android 讨论区','Android系统资讯，Android手机应用及其相关资源的共享',1);
insert into CommunityForum values(4,'IOS 讨论区','苹果ipad、iphone系统资讯，苹果手机应用及其相关资源的共享',1);
insert into CommunityForum values(5,'WP8 讨论区','微软的Windows Phone 8相关资讯和资源讨论',1);
insert into CommunityForum values(6,'其他手机系统讨论区','Web OS、Unbantu、塞班等其他手机操作系统讨论区',1);
insert into CommunityForum values(7,'电子书分享区','电子书、电子杂志资源分享',2);
insert into CommunityForum values(8,'漫画分享区','漫画分享区',2);
insert into CommunityForum values(9,'音乐分享区','mp3、wma等音乐资源分享区',2);
insert into CommunityForum values(10,'电影分享区','热门电影分享区',2);
commit;
create sequence seq_community_forum start with 11 increment by 1;
select * from CommunityForum;

create table CommunityForumMaster(
  Id number(10) primary key,
  ForumId number(3) not null,
  MasterId number(10) not null
);
alter table CommunityForumMaster add constraints fk_Community_ForumMaster_Forum foreign key(ForumId) references CommunityForum(Id);
alter table CommunityForumMaster add constraints fk_Community_ForumMaster_User foreign key(MasterId) references CommunityUser(Id);
/
create sequence seq_community_forum_master start with 1 increment by 1;

create table CommunityTopic(
  Id         number(10) primary key, 
  Title      varchar2(50) not null, 
  Content    varchar2(2000) not null,
  CreateTime timestamp not null,
  ForumId    number(3) not null,
  AuthorId   number(10) not null, 
  IP         varchar2(50) not null,
  CommentTimes number(6) not null,
  UpdateTime timestamp not null,
  Clicks     number(10) not null,
  IsBest     number(1) not null,
  Status     number(1) not null
);
alter table CommunityTopic add constraints fk_Community_Topic_Forum foreign key(ForumId) references CommunityForum(Id);
alter table CommunityTopic add constraints fk_Community_Topic_User foreign key(AuthorId) references CommunityUser(Id);
/
--Topic-01
insert into CommunityTopic values(1,'Android版块版规，请大家认真阅读',
       '请各位版友遵守版块规范，不允许发布反动言论，禁止在板块中争吵，请使用文明语言，谢谢合作。'
       ,to_date('2014-05-05 19:08:32','yyyy-mm-dd hh24:mi:ss'),3,8,'101.23.0.23',20
       ,to_date('2014-05-05 20:55:31','yyyy-mm-dd hh24:mi:ss'),1000,1,1);
--Topic-02
insert into CommunityTopic values(2,'Galaxy Note 4 新功能曝光',
       '同时根据国外网站SamMobile的报道称，三星目前正在使用GALAXY S5测试一些新的软件功能，并打算让这些功能在下一代GALAXY Note上首发。比如该机也会具备防水功能，并且提供了专门的水下拍摄模式，而这意味着该机的防水等级有可能比GALAXY S5还要高。'
       ,to_date('2014-05-05 18:30:32','yyyy-mm-dd hh24:mi:ss'),3,1,'101.23.0.23',20
       ,to_date('2014-05-05 18:50:31','yyyy-mm-dd hh24:mi:ss'),1000,1,1);
--Topic-03
insert into CommunityTopic values(3,'小米发布7.9寸小米平板',
       '如题...'
       ,to_date('2014-05-05 18:45:00','yyyy-mm-dd hh24:mi:ss'),3,2,'101.23.0.24',3
       ,to_date('2014-05-06 18:48:00','yyyy-mm-dd hh24:mi:ss'),100,1,1);
--Topic-04
insert into CommunityTopic values(4,'Galaxy Note 10.1 2014版 开箱测评',
       '稍后上菜...'
       ,to_date('2014-05-05 18:20:00','yyyy-mm-dd hh24:mi:ss'),3,3,'101.23.0.25',3
       ,to_date('2014-05-06 18:23:00','yyyy-mm-dd hh24:mi:ss'),55,1,1);
--Topic-05
insert into CommunityTopic values(5,'锤子科技发布第一代锤子手机',
       '如题...'
       ,to_date('2014-05-05 18:10:00','yyyy-mm-dd hh24:mi:ss'),3,4,'101.23.0.26',4
       ,to_date('2014-05-06 18:11:00','yyyy-mm-dd hh24:mi:ss'),20,1,1);
--Topic-06
insert into CommunityTopic values(6,'三星 I9100 4.0 ROM 资源',
       '如题...'
       ,to_date('2014-05-03 10:12:00','yyyy-mm-dd hh24:mi:ss'),3,5,'101.23.0.26',3
       ,to_date('2014-05-03 10:12:00','yyyy-mm-dd hh24:mi:ss'),20,0,1);
--Topic-07
insert into CommunityTopic values(7,'HTC Desire816 资源汇总贴',
       '如题...'
       ,to_date('2014-05-03 10:13:00','yyyy-mm-dd hh24:mi:ss'),3,6,'101.23.0.26',3
       ,to_date('2014-05-03 10:13:00','yyyy-mm-dd hh24:mi:ss'),20,0,1);
--Topic-08
insert into CommunityTopic values(8,'Nexus 7港行精简ROM',
       '如题...'
       ,to_date('2014-05-03 10:14:00','yyyy-mm-dd hh24:mi:ss'),3,4,'101.23.0.26',3
       ,to_date('2014-05-03 10:14:00','yyyy-mm-dd hh24:mi:ss'),20,0,1);
--Topic-09
insert into CommunityTopic values(9,'港行i9220 4.0.4 精简Rom',
       '如题...'
       ,to_date('2014-05-03 11:10:00','yyyy-mm-dd hh24:mi:ss'),3,5,'101.23.0.26',0
       ,to_date('2014-05-03 11:10:00','yyyy-mm-dd hh24:mi:ss'),20,0,1);
--Topic-10
insert into CommunityTopic values(10,'华为荣耀X到手测评',
       '如题...'
       ,to_date('2014-05-03 11:14:00','yyyy-mm-dd hh24:mi:ss'),3,5,'101.23.0.26',0
       ,to_date('2014-05-03 11:14:00','yyyy-mm-dd hh24:mi:ss'),20,0,1);
--Topic-11
insert into CommunityTopic values(11,'Android版测试主题-01',
       '如题...'
       ,to_date('2014-05-01 10:10:00','yyyy-mm-dd hh24:mi:ss'),3,4,'101.23.0.26',3
       ,to_date('2014-05-01 10:10:00','yyyy-mm-dd hh24:mi:ss'),20,0,1);
--Topic-12
insert into CommunityTopic values(12,'Android版测试主题-02',
       '如题...'
       ,to_date('2014-05-01 10:10:00','yyyy-mm-dd hh24:mi:ss'),3,4,'101.23.0.26',3
       ,to_date('2014-05-01 10:10:00','yyyy-mm-dd hh24:mi:ss'),20,0,1);
--Topic-13
insert into CommunityTopic values(13,'Android版测试主题-03',
       '如题...'
       ,to_date('2014-05-01 10:10:00','yyyy-mm-dd hh24:mi:ss'),3,4,'101.23.0.26',3
       ,to_date('2014-05-01 10:10:00','yyyy-mm-dd hh24:mi:ss'),20,0,1);
--Topic-14
insert into CommunityTopic values(14,'Android版测试主题-04',
       '如题...'
       ,to_date('2014-05-01 10:10:00','yyyy-mm-dd hh24:mi:ss'),3,4,'101.23.0.26',3
       ,to_date('2014-05-01 10:10:00','yyyy-mm-dd hh24:mi:ss'),20,0,1);
--Topic-15
insert into CommunityTopic values(15,'Android版测试主题-05',
       '如题...'
       ,to_date('2014-05-01 10:10:00','yyyy-mm-dd hh24:mi:ss'),3,4,'101.23.0.26',3
       ,to_date('2014-05-01 10:10:00','yyyy-mm-dd hh24:mi:ss'),20,0,1);
--Topic-16
insert into CommunityTopic values(16,'Android版测试主题-06',
       '如题...'
       ,to_date('2014-05-01 10:10:00','yyyy-mm-dd hh24:mi:ss'),3,4,'101.23.0.26',3
       ,to_date('2014-05-01 10:10:00','yyyy-mm-dd hh24:mi:ss'),20,0,1);
--Topic-17
insert into CommunityTopic values(17,'Android版测试主题-07',
       '如题...'
       ,to_date('2014-05-01 10:10:00','yyyy-mm-dd hh24:mi:ss'),3,4,'101.23.0.26',3
       ,to_date('2014-05-01 10:10:00','yyyy-mm-dd hh24:mi:ss'),20,0,1);
--Topic-18
insert into CommunityTopic values(18,'Android版测试主题-08',
       '如题...'
       ,to_date('2014-05-01 10:10:00','yyyy-mm-dd hh24:mi:ss'),3,4,'101.23.0.26',3
       ,to_date('2014-05-01 10:10:00','yyyy-mm-dd hh24:mi:ss'),20,0,1);
--Topic-19
insert into CommunityTopic values(19,'Android版测试主题-09',
       '如题...'
       ,to_date('2014-05-01 10:10:00','yyyy-mm-dd hh24:mi:ss'),3,4,'101.23.0.26',3
       ,to_date('2014-05-01 10:10:00','yyyy-mm-dd hh24:mi:ss'),20,0,1);
--Topic-20
insert into CommunityTopic values(20,'Android版测试主题-20',
       '如题...'
       ,to_date('2014-05-01 10:10:00','yyyy-mm-dd hh24:mi:ss'),3,4,'101.23.0.26',3
       ,to_date('2014-05-01 10:10:00','yyyy-mm-dd hh24:mi:ss'),20,0,1);
commit;
/
create sequence seq_community_topic start with 21 increment by 1;
/
create table CommunityComment(  
  Id            number(10)     primary key,
  Content       varchar2(2000) not null,
  CommentTime    timestamp not null,
  IP            varchar2(50) not null,
  CommentType   number(1) not null,
  ReferenceId   number(10) not null,                  
  CommenterId   number(10) not null,
  Status        number(1) not null
);
alter table CommunityComment add constraints fk_Community_Comment_Topic foreign key(ReferenceId) references CommunityTopic(Id);
alter table CommunityComment add constraints fk_Community_Comment_User foreign key(CommenterId) references CommunityUser(Id);
/
--Topic01-Comments
insert into CommunityComment values(1,'支持',to_date('2014-05-05 20:01:30','yyyy-mm-dd hh24:mi:ss'),'101.1.2.3',1,1,1,1);
insert into CommunityComment values(2,'支持管理员',to_date('2014-05-05 20:02:30','yyyy-mm-dd hh24:mi:ss'),'101.1.2.4',1,1,2,1);
insert into CommunityComment values(3,'支持',to_date('2014-05-05 20:02:30','yyyy-mm-dd hh24:mi:ss'),'101.1.2.4',1,1,3,1);
insert into CommunityComment values(4,'支持。。。',to_date('2014-05-05 20:02:30','yyyy-mm-dd hh24:mi:ss'),'101.1.2.4',1,1,4,1);
insert into CommunityComment values(5,'支持',to_date('2014-05-05 20:02:30','yyyy-mm-dd hh24:mi:ss'),'101.1.2.4',1,1,5,1);
insert into CommunityComment values(6,'支持！',to_date('2014-05-05 20:02:30','yyyy-mm-dd hh24:mi:ss'),'101.1.2.4',1,1,6,1);
insert into CommunityComment values(7,'支持',to_date('2014-05-05 20:02:30','yyyy-mm-dd hh24:mi:ss'),'101.1.2.4',1,1,7,1);
insert into CommunityComment values(8,'支持',to_date('2014-05-05 20:02:30','yyyy-mm-dd hh24:mi:ss'),'101.1.2.4',1,1,8,1);
insert into CommunityComment values(9,'支持管理员',to_date('2014-05-05 20:02:30','yyyy-mm-dd hh24:mi:ss'),'101.1.2.4',1,1,1,1);
insert into CommunityComment values(10,'支持...',to_date('2014-05-05 20:55:31','yyyy-mm-dd hh24:mi:ss'),'101.1.2.4',1,1,2,1);
--Topic02-Comments
insert into CommunityComment values(11,'沙发',to_date('2014-05-05 18:31:30','yyyy-mm-dd hh24:mi:ss'),'101.1.2.3',1,2,2,1);
insert into CommunityComment values(12,'板凳',to_date('2014-05-05 18:32:30','yyyy-mm-dd hh24:mi:ss'),'101.1.2.4',1,2,3,1);
insert into CommunityComment values(13,'地板',to_date('2014-05-05 18:33:31','yyyy-mm-dd hh24:mi:ss'),'101.1.2.5',1,2,4,1);
insert into CommunityComment values(14,'地下室',to_date('2014-05-05 18:34:31','yyyy-mm-dd hh24:mi:ss'),'101.1.2.6',1,2,5,1);
insert into CommunityComment values(15,'负1楼',to_date('2014-05-05 18:35:31','yyyy-mm-dd hh24:mi:ss'),'101.1.2.7',1,2,6,1);
insert into CommunityComment values(16,'负2楼',to_date('2014-05-05 18:36:31','yyyy-mm-dd hh24:mi:ss'),'101.1.2.8',1,2,7,1);
insert into CommunityComment values(17,'负3楼',to_date('2014-05-05 18:37:31','yyyy-mm-dd hh24:mi:ss'),'101.1.2.9',1,2,8,1);
insert into CommunityComment values(18,'负4楼',to_date('2014-05-05 18:38:31','yyyy-mm-dd hh24:mi:ss'),'101.1.2.10',1,2,1,1);
insert into CommunityComment values(19,'负5楼',to_date('2014-05-05 18:39:31','yyyy-mm-dd hh24:mi:ss'),'101.1.2.3',1,2,2,1);
insert into CommunityComment values(20,'负6楼',to_date('2014-05-05 18:40:3','yyyy-mm-dd hh24:mi:ss'),'101.1.2.4',1,2,3,1);
insert into CommunityComment values(21,'负7楼',to_date('2014-05-05 18:41:31','yyyy-mm-dd hh24:mi:ss'),'101.1.2.5',1,2,4,1);
insert into CommunityComment values(22,'负8楼',to_date('2014-05-05 18:42:31','yyyy-mm-dd hh24:mi:ss'),'101.1.2.6',1,2,5,1);
insert into CommunityComment values(23,'负9楼',to_date('2014-05-05 18:43:31','yyyy-mm-dd hh24:mi:ss'),'101.1.2.7',1,2,6,1);
insert into CommunityComment values(24,'负10楼',to_date('2014-05-05 18:44:31','yyyy-mm-dd hh24:mi:ss'),'101.1.2.8',1,2,7,1);
insert into CommunityComment values(25,'负11楼',to_date('2014-05-05 18:45:31','yyyy-mm-dd hh24:mi:ss'),'101.1.2.9',1,2,8,1);
insert into CommunityComment values(26,'负12楼',to_date('2014-05-05 18:46:31','yyyy-mm-dd hh24:mi:ss'),'101.1.2.10',1,2,1,1);
insert into CommunityComment values(27,'负13楼',to_date('2014-05-05 18:47:31','yyyy-mm-dd hh24:mi:ss'),'101.1.2.3',1,2,2,1);
insert into CommunityComment values(28,'负14楼',to_date('2014-05-05 18:48:31','yyyy-mm-dd hh24:mi:ss'),'101.1.2.4',1,2,3,1);
insert into CommunityComment values(29,'负15楼',to_date('2014-05-05 18:49:31','yyyy-mm-dd hh24:mi:ss'),'101.1.2.5',1,2,4,1);
insert into CommunityComment values(30,'负16楼',to_date('2014-05-05 18:50:31','yyyy-mm-dd hh24:mi:ss'),'101.1.2.6',1,2,5,1);
--Topic03-Comments
insert into CommunityComment values(31,'沙发',to_date('2014-05-05 18:31:30','yyyy-mm-dd hh24:mi:ss'),'101.1.2.3',1,3,2,1);
insert into CommunityComment values(32,'板凳',to_date('2014-05-05 18:32:30','yyyy-mm-dd hh24:mi:ss'),'101.1.2.4',1,3,3,1);
insert into CommunityComment values(33,'地板',to_date('2014-05-05 18:33:31','yyyy-mm-dd hh24:mi:ss'),'101.1.2.5',1,3,4,1);
--Topic04-Comments
insert into CommunityComment values(34,'沙发',to_date('2014-05-05 18:31:30','yyyy-mm-dd hh24:mi:ss'),'101.1.2.3',1,4,2,1);
insert into CommunityComment values(35,'板凳',to_date('2014-05-05 18:32:30','yyyy-mm-dd hh24:mi:ss'),'101.1.2.4',1,4,3,1);
insert into CommunityComment values(36,'地板',to_date('2014-05-05 18:33:31','yyyy-mm-dd hh24:mi:ss'),'101.1.2.5',1,4,4,1);
--Topic05-Comments
insert into CommunityComment values(37,'沙发',to_date('2014-05-05 18:31:30','yyyy-mm-dd hh24:mi:ss'),'101.1.2.3',1,5,2,1);
insert into CommunityComment values(38,'板凳',to_date('2014-05-05 18:32:30','yyyy-mm-dd hh24:mi:ss'),'101.1.2.4',1,5,3,1);
insert into CommunityComment values(39,'地板',to_date('2014-05-05 18:33:31','yyyy-mm-dd hh24:mi:ss'),'101.1.2.5',1,5,4,1);
insert into CommunityComment values(40,'地下室',to_date('2014-05-05 18:33:33','yyyy-mm-dd hh24:mi:ss'),'101.1.2.6',5,4,5,1);
commit;
/
create sequence seq_community_comment start with 41 increment by 1;
/

create table CommunityMessageSending(
  Id number(10) primary key,
  Title varchar2(50) not null,
  Content varchar2(2000) not null,
  Receivers varchar2(2000) not null,
  SendTime timestamp,
  SenderId number(10) not null,
  Status number(1) not null
);		
alter table CommunityMessageSending add constraints fk_Community_M_S_User foreign key(SenderId) references CommunityUser(Id);
/
insert into CommunityMessageSending values(1,'欢迎来到XDA社区，请大家准守社区规范，文明发言。','欢迎来到XDA社区，请大家准守社区规范，文明发言......不然，我让城管抽你们!!!^_^','sam,jack,tom,candy,lily,jammy,rose',to_date('2014-05-05 08:08:08','yyyy-mm-dd hh24:mi:ss'),8,1);
insert into CommunityMessageSending values(2,'关于Android版块版主的问题','你的呼声很高哦，有兴趣做Android版块的版主吗？','sam',to_date('2014-05-05 09:09:09','yyyy-mm-dd hh24:mi:ss'),8,1);
insert into CommunityMessageSending values(3,'请问大大','可以给我一份三星n7102的ROM吗？','sam',to_date('2014-05-05 10:10:10','yyyy-mm-dd hh24:mi:ss'),4,1);
insert into CommunityMessageSending values(4,'好久不见，等你发精华贴呢','最近你忙什么去了，好久没有发技术贴了啊','sam',to_date('2014-05-05 09:30:10','yyyy-mm-dd hh24:mi:ss'),5,1);
insert into CommunityMessageSending values(5,'你的手机修好了吗','我的机器也出现你上次说的问题了，求解决方案啊！！！','sam',to_date('2014-05-05 11:30:10','yyyy-mm-dd hh24:mi:ss'),6,1);
insert into CommunityMessageSending values(6,'最近工作忙，没顾上','最近工作忙，没顾上，很快就回归啦，呵呵。','lily',to_date('2014-05-06 11:30:10','yyyy-mm-dd hh24:mi:ss'),1,1);
insert into CommunityMessageSending values(7,'暂时无法承担版主重任','最近比较忙，暂时先不要了，以后有空再商量。','admin',to_date('2014-05-06 11:30:10','yyyy-mm-dd hh24:mi:ss'),1,0);
insert into CommunityMessageSending values(8,'修好了','修好了，回头给你发解决方案，呵呵','jammy',to_date('2014-05-06 11:30:10','yyyy-mm-dd hh24:mi:ss'),1,2);
insert into CommunityMessageSending values(9,'我没有这个版本的ROM','不好意思，帮不了你，呵呵','candy',to_date('2014-05-05 11:30:10','yyyy-mm-dd hh24:mi:ss'),1,3);
commit;
create sequence seq_community_message_sending start with 10 increment by 1;
/

create table CommunityMessageReceiving(
  Id number(10) primary key,
  MessageSendingId number(10) not null,
  ReceiverId number(10) not null,
  Readed number(1) not null,
  Status number(1) not null
);
alter table CommunityMessageReceiving add constraints fk_Community_M_R_User foreign key(ReceiverId) references CommunityUser(Id);
/
insert into CommunityMessageReceiving values(1,1,1,1,1);
insert into CommunityMessageReceiving values(2,1,2,0,1);
insert into CommunityMessageReceiving values(3,1,3,1,2);
insert into CommunityMessageReceiving values(4,1,4,0,1);
insert into CommunityMessageReceiving values(5,1,5,1,1);
insert into CommunityMessageReceiving values(6,1,6,0,1);
insert into CommunityMessageReceiving values(7,1,7,1,1);

insert into CommunityMessageReceiving values(8,2,1,0,1);
insert into CommunityMessageReceiving values(9,3,1,0,1);
insert into CommunityMessageReceiving values(10,4,1,1,2);
insert into CommunityMessageReceiving values(11,5,1,1,3);
insert into CommunityMessageReceiving values(12,6,5,0,1);
insert into CommunityMessageReceiving values(13,8,6,1,1);
insert into CommunityMessageReceiving values(14,9,4,1,1);

commit;
create sequence seq_community_message_recei start with 15 increment by 1;
