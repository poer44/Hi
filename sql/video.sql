--tb_videotype（视频类型表）
create table tb_videotype(
       typeid number(10) primary key, --typeid（sequence编号）
       typename varchar2(100)         --typename1（类型名称）
);
--创建序列seq_typeid
create sequence seq_typeid
start with 1 increment by 1
minvalue 1 maxvalue 999999999
nocycle cache 20;

--tb_video（视频信息表）
create table tb_video(
       videoid number(10) primary key,--videoid(sequence编号)
       videoname varchar2(200),   --videoname（名称）
       author varchar2(100) ,  --author（作者）
       imgurl varchar2(4000),   --img（封面路径）
       fileurl varchar2(4000),    --videofile（视频路径）
       filesize varchar2(10),   --filesize（视频大小）
       format varchar2(20),   --format（视频格式）
       play number(10),       --play（播放量）
       collection number(10),  --collection（收藏量）
       uptime varchar2(100),   --uptime（上传时间）
       typeid number(10)   --typeid（视频类型,外键）
);
alter table tb_video
add (videolength varchar2(30) );  --videolength（视频长度）

alter table tb_video
modify (videolength varchar2(100) default sysdate);

--创建序列seq_videoid
create sequence seq_videoid
start with 1 increment by 1 
minvalue 1 maxvalue 999999999 
nocycle cache 20;
--typeid（视频类型,外键）
alter table tb_video 
      add constraint fk_typeid foreign key(typeid)
      references tb_videotype(typeid);

--tb_user（用户信息表）
create table tb_user(
       userid varchar2(20) primary key,--userid（用户账号）
       password varchar2(20),   --password（密码）
       username varchar2(20) ,  --,username（昵称）
       imgurl varchar2(4000),   --img（头像）
       sex varchar2(10),    --sex（性别）
       birthday varchar2(10),   --birthday（生日）,
       address varchar2(20),   --address（地址）
       emotion varchar2(20),       --emotion（感情状况）
       email varchar2(50),  --email（邮箱）,
       sign varchar2(200)   --sign（个性签名）
);
alter table tb_user
add (registertime varchar2(30)  );   --registertime（注册时间）
alter table tb_user
modify (registertime varchar2(100) default sysdate);

--tb_admin(管理员)
create table tb_admin(
       adminid varchar2(20) primary key, --adminid(管理员账号)
       password varchar2(20)   --password（密码）
);

--tb_usercollection（用户收藏表）
create table tb_usercollection(
      userid varchar2(20), --userid（用户账号,外键)
      collection varchar2(4000) --collection（收藏列表）
);
--userid（用户账号,外键)
alter table tb_usercollection
      add constraint fk_collectionid foreign key(userid)
      references tb_user(userid);
      
--tb_videocomment（视频评论表）
create table tb_videocomment(
      commentid number(10) primary key,--(sequence编号)
      videoid number(10), --（视频编号，外键）
      userid varchar2(20), --userid（用户账号,外键)
      content varchar2(200),--content（评价内容）
      commenttime varchar2(100)  --commenttime评价时间
);
alter table tb_videocomment
modify (commenttime varchar2(100) default sysdate);
--(sequence编号)
create sequence seq_commentid
start with 1 increment by 1
minvalue 1 maxvalue 999999999
nocycle cache 20;
--（视频编号，外键）
alter table tb_videocomment
      add constraint fk_comment_videoid foreign key(videoid)
      references tb_video(videoid);
--userid（用户账号,外键)
alter table tb_videocomment
      add constraint fk_comment_userid foreign key(userid)
      references tb_user(userid);
--tb_submission投稿表
create table tb_submission(
       submissionid number(10) primary key,--(sequence编号)
       userid varchar2(20), --userid（用户账号,外键)
       videoname varchar2(200),   --videoname（名称）
       imgurl varchar2(4000),   --img（封面路径）
       fileurl varchar2(4000),    --videofile（视频路径）
       filesize varchar2(10),   --filesize（视频大小）
       format varchar2(20),   --format（视频格式）
       submissiontime varchar2(100), --submissiontime（投稿时间）
       state varchar2(10) --state（状态）
);
alter table tb_submission
modify (submissiontime varchar2(100) default sysdate);
--(sequence编号)
create sequence seq_submissionid
start with 1 increment by 1
minvalue 1 maxvalue 999999999
nocycle cache 20;
--userid（用户账号,外键)
alter table tb_submission
       add constraint fk_submission_userid foreign key(userid) 
       references tb_user(userid);       
--tb_barrage弹幕表
create table tb_barrage(
      barrageid number(10) primary key,--(sequence编号)
      videoid number(10), --（视频编号，外键）
      userid varchar2(20), --userid（用户账号,外键)
      content varchar2(200),--content（弹幕内容）
      videotime varchar2(100),  --videotime（视频里的时间）
      barragetime varchar2(100)  -- barragetime（发表弹幕的日期）
);
alter table tb_barrage
modify (barragetime varchar2(100) default sysdate);
--(sequence编号)
create sequence seq_barrageid
start with 1 increment by 1
minvalue 1 maxvalue 999999999
nocycle cache 20;
--（视频编号，外键）
alter table tb_barrage
      add constraint fk_barrage_videoid foreign key(videoid)
      references tb_video(videoid);
--userid（用户账号,外键)
alter table tb_barrage
      add constraint fk_barrage_userid foreign key(userid)
      references tb_user(userid);
  








