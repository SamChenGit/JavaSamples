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
insert into CommunityRole values(1,'��ͨ�û�');
insert into CommunityRole values(2,'����');
insert into CommunityRole values(3,'����Ա');
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
insert into CommunityUser values(1,'sam','����ү','123','��ɮϴͷ��Ʈ��','tang3@126.com',10001,1,1);
insert into CommunityUser values(2,'jack','������','123','���Ҵ��壬��Զ����...','li4@126.com',1001,1,1);
insert into CommunityUser values(3,'tom','��˵����tomcat','123','�����ѧ��˵��...','wang5@126.com',101,1,1);
insert into CommunityUser values(4,'candy','�ǹ�СM','123','��ֽҲ���ֻ���','candy5@126.com',101,1,1);
insert into CommunityUser values(5,'lily','����˿','123','����ϵ����С��','lily5@126.com',101,1,1);
insert into CommunityUser values(6,'jammy','��������','123','��������......��Ϣ����','jammy@126.com',101,1,1);
insert into CommunityUser values(7,'rose','���õ��','123','��õ�����õ��','rose@126.com',101,1,1);
insert into CommunityUser values(8,'admin','�����Ա','123','������ǧ�ǹܣ����������ƽ','zhao6@126.com',10001,3,1);
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
insert into CommunityForum values(1,'�����ֻ�OS������','�����ֻ�����ϵͳ��������Ѷ,������Դ',null);
insert into CommunityForum values(2,'������Դ������','�����ֻ�������Դ����Ӱ�����֡������顢������ͨ�õ���Դ',null);
insert into CommunityForum values(3,'Android ������','Androidϵͳ��Ѷ��Android�ֻ�Ӧ�ü��������Դ�Ĺ���',1);
insert into CommunityForum values(4,'IOS ������','ƻ��ipad��iphoneϵͳ��Ѷ��ƻ���ֻ�Ӧ�ü��������Դ�Ĺ���',1);
insert into CommunityForum values(5,'WP8 ������','΢���Windows Phone 8�����Ѷ����Դ����',1);
insert into CommunityForum values(6,'�����ֻ�ϵͳ������','Web OS��Unbantu������������ֻ�����ϵͳ������',1);
insert into CommunityForum values(7,'�����������','�����顢������־��Դ����',2);
insert into CommunityForum values(8,'����������','����������',2);
insert into CommunityForum values(9,'���ַ�����','mp3��wma��������Դ������',2);
insert into CommunityForum values(10,'��Ӱ������','���ŵ�Ӱ������',2);
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
insert into CommunityTopic values(1,'Android����棬���������Ķ�',
       '���λ�������ذ��淶�����������������ۣ���ֹ�ڰ������������ʹ���������ԣ�лл������'
       ,to_date('2014-05-05 19:08:32','yyyy-mm-dd hh24:mi:ss'),3,8,'101.23.0.23',20
       ,to_date('2014-05-05 20:55:31','yyyy-mm-dd hh24:mi:ss'),1000,1,1);
--Topic-02
insert into CommunityTopic values(2,'Galaxy Note 4 �¹����ع�',
       'ͬʱ���ݹ�����վSamMobile�ı����ƣ�����Ŀǰ����ʹ��GALAXY S5����һЩ�µ�������ܣ�����������Щ��������һ��GALAXY Note���׷�������û�Ҳ��߱���ˮ���ܣ������ṩ��ר�ŵ�ˮ������ģʽ��������ζ�Ÿû��ķ�ˮ�ȼ��п��ܱ�GALAXY S5��Ҫ�ߡ�'
       ,to_date('2014-05-05 18:30:32','yyyy-mm-dd hh24:mi:ss'),3,1,'101.23.0.23',20
       ,to_date('2014-05-05 18:50:31','yyyy-mm-dd hh24:mi:ss'),1000,1,1);
--Topic-03
insert into CommunityTopic values(3,'С�׷���7.9��С��ƽ��',
       '����...'
       ,to_date('2014-05-05 18:45:00','yyyy-mm-dd hh24:mi:ss'),3,2,'101.23.0.24',3
       ,to_date('2014-05-06 18:48:00','yyyy-mm-dd hh24:mi:ss'),100,1,1);
--Topic-04
insert into CommunityTopic values(4,'Galaxy Note 10.1 2014�� �������',
       '�Ժ��ϲ�...'
       ,to_date('2014-05-05 18:20:00','yyyy-mm-dd hh24:mi:ss'),3,3,'101.23.0.25',3
       ,to_date('2014-05-06 18:23:00','yyyy-mm-dd hh24:mi:ss'),55,1,1);
--Topic-05
insert into CommunityTopic values(5,'���ӿƼ�������һ�������ֻ�',
       '����...'
       ,to_date('2014-05-05 18:10:00','yyyy-mm-dd hh24:mi:ss'),3,4,'101.23.0.26',4
       ,to_date('2014-05-06 18:11:00','yyyy-mm-dd hh24:mi:ss'),20,1,1);
--Topic-06
insert into CommunityTopic values(6,'���� I9100 4.0 ROM ��Դ',
       '����...'
       ,to_date('2014-05-03 10:12:00','yyyy-mm-dd hh24:mi:ss'),3,5,'101.23.0.26',3
       ,to_date('2014-05-03 10:12:00','yyyy-mm-dd hh24:mi:ss'),20,0,1);
--Topic-07
insert into CommunityTopic values(7,'HTC Desire816 ��Դ������',
       '����...'
       ,to_date('2014-05-03 10:13:00','yyyy-mm-dd hh24:mi:ss'),3,6,'101.23.0.26',3
       ,to_date('2014-05-03 10:13:00','yyyy-mm-dd hh24:mi:ss'),20,0,1);
--Topic-08
insert into CommunityTopic values(8,'Nexus 7���о���ROM',
       '����...'
       ,to_date('2014-05-03 10:14:00','yyyy-mm-dd hh24:mi:ss'),3,4,'101.23.0.26',3
       ,to_date('2014-05-03 10:14:00','yyyy-mm-dd hh24:mi:ss'),20,0,1);
--Topic-09
insert into CommunityTopic values(9,'����i9220 4.0.4 ����Rom',
       '����...'
       ,to_date('2014-05-03 11:10:00','yyyy-mm-dd hh24:mi:ss'),3,5,'101.23.0.26',0
       ,to_date('2014-05-03 11:10:00','yyyy-mm-dd hh24:mi:ss'),20,0,1);
--Topic-10
insert into CommunityTopic values(10,'��Ϊ��ҫX���ֲ���',
       '����...'
       ,to_date('2014-05-03 11:14:00','yyyy-mm-dd hh24:mi:ss'),3,5,'101.23.0.26',0
       ,to_date('2014-05-03 11:14:00','yyyy-mm-dd hh24:mi:ss'),20,0,1);
--Topic-11
insert into CommunityTopic values(11,'Android���������-01',
       '����...'
       ,to_date('2014-05-01 10:10:00','yyyy-mm-dd hh24:mi:ss'),3,4,'101.23.0.26',3
       ,to_date('2014-05-01 10:10:00','yyyy-mm-dd hh24:mi:ss'),20,0,1);
--Topic-12
insert into CommunityTopic values(12,'Android���������-02',
       '����...'
       ,to_date('2014-05-01 10:10:00','yyyy-mm-dd hh24:mi:ss'),3,4,'101.23.0.26',3
       ,to_date('2014-05-01 10:10:00','yyyy-mm-dd hh24:mi:ss'),20,0,1);
--Topic-13
insert into CommunityTopic values(13,'Android���������-03',
       '����...'
       ,to_date('2014-05-01 10:10:00','yyyy-mm-dd hh24:mi:ss'),3,4,'101.23.0.26',3
       ,to_date('2014-05-01 10:10:00','yyyy-mm-dd hh24:mi:ss'),20,0,1);
--Topic-14
insert into CommunityTopic values(14,'Android���������-04',
       '����...'
       ,to_date('2014-05-01 10:10:00','yyyy-mm-dd hh24:mi:ss'),3,4,'101.23.0.26',3
       ,to_date('2014-05-01 10:10:00','yyyy-mm-dd hh24:mi:ss'),20,0,1);
--Topic-15
insert into CommunityTopic values(15,'Android���������-05',
       '����...'
       ,to_date('2014-05-01 10:10:00','yyyy-mm-dd hh24:mi:ss'),3,4,'101.23.0.26',3
       ,to_date('2014-05-01 10:10:00','yyyy-mm-dd hh24:mi:ss'),20,0,1);
--Topic-16
insert into CommunityTopic values(16,'Android���������-06',
       '����...'
       ,to_date('2014-05-01 10:10:00','yyyy-mm-dd hh24:mi:ss'),3,4,'101.23.0.26',3
       ,to_date('2014-05-01 10:10:00','yyyy-mm-dd hh24:mi:ss'),20,0,1);
--Topic-17
insert into CommunityTopic values(17,'Android���������-07',
       '����...'
       ,to_date('2014-05-01 10:10:00','yyyy-mm-dd hh24:mi:ss'),3,4,'101.23.0.26',3
       ,to_date('2014-05-01 10:10:00','yyyy-mm-dd hh24:mi:ss'),20,0,1);
--Topic-18
insert into CommunityTopic values(18,'Android���������-08',
       '����...'
       ,to_date('2014-05-01 10:10:00','yyyy-mm-dd hh24:mi:ss'),3,4,'101.23.0.26',3
       ,to_date('2014-05-01 10:10:00','yyyy-mm-dd hh24:mi:ss'),20,0,1);
--Topic-19
insert into CommunityTopic values(19,'Android���������-09',
       '����...'
       ,to_date('2014-05-01 10:10:00','yyyy-mm-dd hh24:mi:ss'),3,4,'101.23.0.26',3
       ,to_date('2014-05-01 10:10:00','yyyy-mm-dd hh24:mi:ss'),20,0,1);
--Topic-20
insert into CommunityTopic values(20,'Android���������-20',
       '����...'
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
insert into CommunityComment values(1,'֧��',to_date('2014-05-05 20:01:30','yyyy-mm-dd hh24:mi:ss'),'101.1.2.3',1,1,1,1);
insert into CommunityComment values(2,'֧�ֹ���Ա',to_date('2014-05-05 20:02:30','yyyy-mm-dd hh24:mi:ss'),'101.1.2.4',1,1,2,1);
insert into CommunityComment values(3,'֧��',to_date('2014-05-05 20:02:30','yyyy-mm-dd hh24:mi:ss'),'101.1.2.4',1,1,3,1);
insert into CommunityComment values(4,'֧�֡�����',to_date('2014-05-05 20:02:30','yyyy-mm-dd hh24:mi:ss'),'101.1.2.4',1,1,4,1);
insert into CommunityComment values(5,'֧��',to_date('2014-05-05 20:02:30','yyyy-mm-dd hh24:mi:ss'),'101.1.2.4',1,1,5,1);
insert into CommunityComment values(6,'֧�֣�',to_date('2014-05-05 20:02:30','yyyy-mm-dd hh24:mi:ss'),'101.1.2.4',1,1,6,1);
insert into CommunityComment values(7,'֧��',to_date('2014-05-05 20:02:30','yyyy-mm-dd hh24:mi:ss'),'101.1.2.4',1,1,7,1);
insert into CommunityComment values(8,'֧��',to_date('2014-05-05 20:02:30','yyyy-mm-dd hh24:mi:ss'),'101.1.2.4',1,1,8,1);
insert into CommunityComment values(9,'֧�ֹ���Ա',to_date('2014-05-05 20:02:30','yyyy-mm-dd hh24:mi:ss'),'101.1.2.4',1,1,1,1);
insert into CommunityComment values(10,'֧��...',to_date('2014-05-05 20:55:31','yyyy-mm-dd hh24:mi:ss'),'101.1.2.4',1,1,2,1);
--Topic02-Comments
insert into CommunityComment values(11,'ɳ��',to_date('2014-05-05 18:31:30','yyyy-mm-dd hh24:mi:ss'),'101.1.2.3',1,2,2,1);
insert into CommunityComment values(12,'���',to_date('2014-05-05 18:32:30','yyyy-mm-dd hh24:mi:ss'),'101.1.2.4',1,2,3,1);
insert into CommunityComment values(13,'�ذ�',to_date('2014-05-05 18:33:31','yyyy-mm-dd hh24:mi:ss'),'101.1.2.5',1,2,4,1);
insert into CommunityComment values(14,'������',to_date('2014-05-05 18:34:31','yyyy-mm-dd hh24:mi:ss'),'101.1.2.6',1,2,5,1);
insert into CommunityComment values(15,'��1¥',to_date('2014-05-05 18:35:31','yyyy-mm-dd hh24:mi:ss'),'101.1.2.7',1,2,6,1);
insert into CommunityComment values(16,'��2¥',to_date('2014-05-05 18:36:31','yyyy-mm-dd hh24:mi:ss'),'101.1.2.8',1,2,7,1);
insert into CommunityComment values(17,'��3¥',to_date('2014-05-05 18:37:31','yyyy-mm-dd hh24:mi:ss'),'101.1.2.9',1,2,8,1);
insert into CommunityComment values(18,'��4¥',to_date('2014-05-05 18:38:31','yyyy-mm-dd hh24:mi:ss'),'101.1.2.10',1,2,1,1);
insert into CommunityComment values(19,'��5¥',to_date('2014-05-05 18:39:31','yyyy-mm-dd hh24:mi:ss'),'101.1.2.3',1,2,2,1);
insert into CommunityComment values(20,'��6¥',to_date('2014-05-05 18:40:3','yyyy-mm-dd hh24:mi:ss'),'101.1.2.4',1,2,3,1);
insert into CommunityComment values(21,'��7¥',to_date('2014-05-05 18:41:31','yyyy-mm-dd hh24:mi:ss'),'101.1.2.5',1,2,4,1);
insert into CommunityComment values(22,'��8¥',to_date('2014-05-05 18:42:31','yyyy-mm-dd hh24:mi:ss'),'101.1.2.6',1,2,5,1);
insert into CommunityComment values(23,'��9¥',to_date('2014-05-05 18:43:31','yyyy-mm-dd hh24:mi:ss'),'101.1.2.7',1,2,6,1);
insert into CommunityComment values(24,'��10¥',to_date('2014-05-05 18:44:31','yyyy-mm-dd hh24:mi:ss'),'101.1.2.8',1,2,7,1);
insert into CommunityComment values(25,'��11¥',to_date('2014-05-05 18:45:31','yyyy-mm-dd hh24:mi:ss'),'101.1.2.9',1,2,8,1);
insert into CommunityComment values(26,'��12¥',to_date('2014-05-05 18:46:31','yyyy-mm-dd hh24:mi:ss'),'101.1.2.10',1,2,1,1);
insert into CommunityComment values(27,'��13¥',to_date('2014-05-05 18:47:31','yyyy-mm-dd hh24:mi:ss'),'101.1.2.3',1,2,2,1);
insert into CommunityComment values(28,'��14¥',to_date('2014-05-05 18:48:31','yyyy-mm-dd hh24:mi:ss'),'101.1.2.4',1,2,3,1);
insert into CommunityComment values(29,'��15¥',to_date('2014-05-05 18:49:31','yyyy-mm-dd hh24:mi:ss'),'101.1.2.5',1,2,4,1);
insert into CommunityComment values(30,'��16¥',to_date('2014-05-05 18:50:31','yyyy-mm-dd hh24:mi:ss'),'101.1.2.6',1,2,5,1);
--Topic03-Comments
insert into CommunityComment values(31,'ɳ��',to_date('2014-05-05 18:31:30','yyyy-mm-dd hh24:mi:ss'),'101.1.2.3',1,3,2,1);
insert into CommunityComment values(32,'���',to_date('2014-05-05 18:32:30','yyyy-mm-dd hh24:mi:ss'),'101.1.2.4',1,3,3,1);
insert into CommunityComment values(33,'�ذ�',to_date('2014-05-05 18:33:31','yyyy-mm-dd hh24:mi:ss'),'101.1.2.5',1,3,4,1);
--Topic04-Comments
insert into CommunityComment values(34,'ɳ��',to_date('2014-05-05 18:31:30','yyyy-mm-dd hh24:mi:ss'),'101.1.2.3',1,4,2,1);
insert into CommunityComment values(35,'���',to_date('2014-05-05 18:32:30','yyyy-mm-dd hh24:mi:ss'),'101.1.2.4',1,4,3,1);
insert into CommunityComment values(36,'�ذ�',to_date('2014-05-05 18:33:31','yyyy-mm-dd hh24:mi:ss'),'101.1.2.5',1,4,4,1);
--Topic05-Comments
insert into CommunityComment values(37,'ɳ��',to_date('2014-05-05 18:31:30','yyyy-mm-dd hh24:mi:ss'),'101.1.2.3',1,5,2,1);
insert into CommunityComment values(38,'���',to_date('2014-05-05 18:32:30','yyyy-mm-dd hh24:mi:ss'),'101.1.2.4',1,5,3,1);
insert into CommunityComment values(39,'�ذ�',to_date('2014-05-05 18:33:31','yyyy-mm-dd hh24:mi:ss'),'101.1.2.5',1,5,4,1);
insert into CommunityComment values(40,'������',to_date('2014-05-05 18:33:33','yyyy-mm-dd hh24:mi:ss'),'101.1.2.6',5,4,5,1);
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
insert into CommunityMessageSending values(1,'��ӭ����XDA����������׼�������淶���������ԡ�','��ӭ����XDA����������׼�������淶����������......��Ȼ�����óǹܳ�����!!!^_^','sam,jack,tom,candy,lily,jammy,rose',to_date('2014-05-05 08:08:08','yyyy-mm-dd hh24:mi:ss'),8,1);
insert into CommunityMessageSending values(2,'����Android������������','��ĺ����ܸ�Ŷ������Ȥ��Android���İ�����','sam',to_date('2014-05-05 09:09:09','yyyy-mm-dd hh24:mi:ss'),8,1);
insert into CommunityMessageSending values(3,'���ʴ��','���Ը���һ������n7102��ROM��','sam',to_date('2014-05-05 10:10:10','yyyy-mm-dd hh24:mi:ss'),4,1);
insert into CommunityMessageSending values(4,'�þò��������㷢��������','�����æʲôȥ�ˣ��þ�û�з��������˰�','sam',to_date('2014-05-05 09:30:10','yyyy-mm-dd hh24:mi:ss'),5,1);
insert into CommunityMessageSending values(5,'����ֻ��޺�����','�ҵĻ���Ҳ�������ϴ�˵�������ˣ�����������������','sam',to_date('2014-05-05 11:30:10','yyyy-mm-dd hh24:mi:ss'),6,1);
insert into CommunityMessageSending values(6,'�������æ��û����','�������æ��û���ϣ��ܿ�ͻع������Ǻǡ�','lily',to_date('2014-05-06 11:30:10','yyyy-mm-dd hh24:mi:ss'),1,1);
insert into CommunityMessageSending values(7,'��ʱ�޷��е���������','����Ƚ�æ����ʱ�Ȳ�Ҫ�ˣ��Ժ��п���������','admin',to_date('2014-05-06 11:30:10','yyyy-mm-dd hh24:mi:ss'),1,0);
insert into CommunityMessageSending values(8,'�޺���','�޺��ˣ���ͷ���㷢����������Ǻ�','jammy',to_date('2014-05-06 11:30:10','yyyy-mm-dd hh24:mi:ss'),1,2);
insert into CommunityMessageSending values(9,'��û������汾��ROM','������˼���ﲻ���㣬�Ǻ�','candy',to_date('2014-05-05 11:30:10','yyyy-mm-dd hh24:mi:ss'),1,3);
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
