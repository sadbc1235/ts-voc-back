CREATE TABLE IF NOT EXISTS `t_user` (
    `user_seq`				BIGINT(10)		UNSIGNED    NOT NULL AUTO_INCREMENT                                         COMMENT '사용자 시퀀스'
    , `comp_seq`			VARCHAR(32)			    	NOT	NULL 				                                        COMMENT '회사 시퀀스'
    , `login_id`			VARCHAR(128)			    NOT	NULL 				                                        COMMENT '로그인 아이디'
    , `create_dt`	        TIMESTAMP					NOT NULL DEFAULT CURRENT_TIMESTAMP								COMMENT '생성일시'
    , `modify_dt`	        TIMESTAMP					NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP	COMMENT '수정일시'
    , PRIMARY KEY	(`user_seq`)
    , KEY			(`comp_seq`)
    , KEY			(`login_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COMMENT='사용자 정보';
ALTER TABLE `t_user`   ADD COLUMN IF NOT EXISTS `user_name`			VARCHAR(256)	NOT NULL    COMMENT '사용자명'    AFTER `login_id`;
ALTER TABLE `t_user`   ADD COLUMN IF NOT EXISTS `pwd`				VARCHAR(256)	NOT NULL    COMMENT '비밀번호'    AFTER `user_name`;
ALTER TABLE `t_user`   ADD COLUMN IF NOT EXISTS `email`				VARCHAR(256)		NULL    COMMENT '이메일'     AFTER `pwd`;
ALTER TABLE `t_user`   ADD COLUMN IF NOT EXISTS `role`				VARCHAR(128)	NOT	NULL    COMMENT '권한'       AFTER `email`;
ALTER TABLE `t_user`   ADD COLUMN IF NOT EXISTS `allow_yn`			VARCHAR(1)		NOT	NULL	default 'N'    COMMENT '승인여부'    AFTER `role`;