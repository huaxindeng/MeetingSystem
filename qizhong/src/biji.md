----------
create table meeting(
  id int auto_increment primary key ,
  time date not null ,
  hour int not null,
  place varchar(32) not null ,
  hotel varchar(32)
);
---------------
create table attendees(
  id int primary key auto_increment,
  name varchar(32) not null ,
  workplace varchar(32) not null ,
  identify_id varchar(20) not null ,
  phone varchar(20) not null,
  meetTime date not null,
  sex varchar(2) check (sex in('男','女')),
  room int check(room in('0','1')),
  meetingID int not null,
  foreign key (meetingID) references meeting(id)
);
  insert into attendees()
  values
          (1,'张三','百度','1001','13746894561','2019-11-20','男',0,1),
          (2,'李四','阿里','1002','13716684896','2019-11-20','男',0,1),
          (3,'王五','京东','1003','18846126846','2019-11-21','男',1,2),
          (4,'赵六','字节跳动','1004','13716549863','2019-11-22','男',0,3),
          (5,'李红','网易','1005','13716854698','2019-11-23','女',0,4)
--------------------------------
create table userlogin(
  `userID` int(11) NOT NULL AUTO_INCREMENT,
  `userName` varchar(200) NOT NULL,
  `password` varchar(200) NOT NULL,
  `role` int(11) NOT NULL DEFAULT '2' COMMENT '角色权限',
PRIMARY KEY (`userID`),
KEY `role` (`role`),
FOREIGN KEY (`role`) REFERENCES `role` (`roleID`)
);

INSERT INTO `userlogin` VALUES ('1', 'admin', '123', '0');
INSERT INTO `userlogin` VALUES ('8', '10001', '123', '2');
INSERT INTO `userlogin` VALUES ('9', '10002', '123', '2');
INSERT INTO `userlogin` VALUES ('10', '10003', '123', '2');
INSERT INTO `userlogin` VALUES ('11', '10005', '123', '2');
INSERT INTO `userlogin` VALUES ('12', '10004', '123', '2');
INSERT INTO `userlogin` VALUES ('13', '10006', '123', '2');
INSERT INTO `userlogin` VALUES ('14', '1001', '123', '1');
INSERT INTO `userlogin` VALUES ('15', '1002', '123', '1');
INSERT INTO `userlogin` VALUES ('16', '1003', '123', '1');
SET FOREIGN_KEY_CHECKS=1;
------------------------------------------          
