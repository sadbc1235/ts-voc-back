CREATE TABLE IF NOT EXISTS `t_post` (
    `post_seq`				BIGINT(10)		UNSIGNED    NOT NULL AUTO_INCREMENT                                         COMMENT '게시물 시퀀스'
    , `comp_seq`			BIGINT(10)		UNSIGNED	NOT	NULL 				                                        COMMENT '회사 시퀀스'
    , `user_seq`			BIGINT(10)		UNSIGNED	NOT	NULL 				                                        COMMENT '사용자 시퀀스'
    , `module_seq`			BIGINT(10)		UNSIGNED	NOT	NULL 				                                        COMMENT '모듈 시퀀스'
    , `create_seq`	        BIGINT(10)		UNSIGNED	NOT NULL DEFAULT CURRENT_TIMESTAMP								COMMENT '생성자'
    , `create_dt`	        TIMESTAMP					NOT NULL DEFAULT CURRENT_TIMESTAMP								COMMENT '생성일시'
    , `modify_seq`	        BIGINT(10)		UNSIGNED	NOT NULL DEFAULT CURRENT_TIMESTAMP								COMMENT '수정자'
    , `modify_dt`	        TIMESTAMP					NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP	COMMENT '수정일시'
    , PRIMARY KEY	(`post_seq`)
    , KEY			(`comp_seq`)
    , KEY			(`user_seq`)
    , KEY			(`module_seq`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COMMENT='게시물 정보';
ALTER TABLE `t_post`   ADD COLUMN IF NOT EXISTS `module_name`	VARCHAR(128)	NOT NULL    COMMENT '모듈명'    	AFTER `module_seq`;
ALTER TABLE `t_post`   ADD COLUMN IF NOT EXISTS `sn_no`			VARCHAR(32)		NOT NULL    COMMENT '우선순위'    	AFTER `module_name`;
ALTER TABLE `t_post`   ADD COLUMN IF NOT EXISTS `title`			VARCHAR(512)	NOT NULL    COMMENT '제목'    	AFTER `sn_no`;
ALTER TABLE `t_post`   ADD COLUMN IF NOT EXISTS `content`		MEDIUMTEXT		NOT NULL    COMMENT '본문'    	AFTER `title`;
ALTER TABLE `t_post`   ADD COLUMN IF NOT EXISTS `state`			VARCHAR(32)		NOT NULL	DEFAULT 'new'    COMMENT '상태'    	AFTER `content`;
ALTER TABLE `t_post`   ADD COLUMN IF NOT EXISTS `del_yn`		CHAR(1)			NOT NULL    COMMENT '삭제여부'    	AFTER `state`;

CREATE TABLE IF NOT EXISTS `t_post_content_history` (
    `post_content_history_seq`	BIGINT(10)		UNSIGNED    NOT NULL AUTO_INCREMENT                                         COMMENT '게시물 수정 기록 시퀀스'
    , `post_seq`				BIGINT(10)		UNSIGNED	NOT	NULL 				                                        COMMENT '게시물 시퀀스'
    , `comp_seq`				BIGINT(10)		UNSIGNED	NOT	NULL 				                                        COMMENT '회사 시퀀스'
    , `user_seq`				BIGINT(10)		UNSIGNED	NOT	NULL 				                                        COMMENT '사용자 시퀀스'
    , `create_seq`	        	BIGINT(10)		UNSIGNED	NOT NULL DEFAULT CURRENT_TIMESTAMP								COMMENT '생성자'
    , `create_dt`	        	TIMESTAMP					NOT NULL DEFAULT CURRENT_TIMESTAMP								COMMENT '생성일시'
    , `modify_seq`	        	BIGINT(10)		UNSIGNED	NOT NULL DEFAULT CURRENT_TIMESTAMP								COMMENT '수정자'
    , `modify_dt`	        	TIMESTAMP					NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP	COMMENT '수정일시'
    , PRIMARY KEY	(`post_content_history_seq`)
    , KEY			(`post_seq`)
    , KEY			(`comp_seq`)
    , KEY			(`user_seq`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COMMENT='게시물 수정 기록';
ALTER TABLE `t_post_content_history`   ADD COLUMN IF NOT EXISTS `content`	MEDIUMTEXT	NOT NULL	COMMENT '본문'	AFTER `user_seq`;

CREATE TABLE IF NOT EXISTS `t_post_attach` (
    `post_attach_seq`		BIGINT(10)		UNSIGNED    NOT NULL AUTO_INCREMENT                                         COMMENT '게시물 첨부파일 시퀀스'
    , `post_seq`			BIGINT(10)		UNSIGNED	NOT	NULL 				                                        COMMENT '게시물 시퀀스'
    , `comp_seq`			BIGINT(10)		UNSIGNED	NOT	NULL 				                                        COMMENT '회사 시퀀스'
    , `user_seq`			BIGINT(10)		UNSIGNED	NOT	NULL 				                                        COMMENT '사용자 시퀀스'
    , `create_seq`	        BIGINT(10)		UNSIGNED	NOT NULL DEFAULT CURRENT_TIMESTAMP								COMMENT '생성자'
    , `create_dt`	        TIMESTAMP					NOT NULL DEFAULT CURRENT_TIMESTAMP								COMMENT '생성일시'
    , `modify_seq`	        BIGINT(10)		UNSIGNED	NOT NULL DEFAULT CURRENT_TIMESTAMP								COMMENT '수정자'
    , `modify_dt`	        TIMESTAMP					NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP	COMMENT '수정일시'
    , PRIMARY KEY	(`post_attach_seq`)
    , KEY			(`post_seq`)
    , KEY			(`comp_seq`)
    , KEY			(`user_seq`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COMMENT='게시물 첨부파일 정보';
ALTER TABLE `t_post_attach`   ADD COLUMN IF NOT EXISTS `display_name`	VARCHAR(128)	NOT NULL	COMMENT '첨부파일명(사용자 조회 파일명)'	AFTER `user_seq`;
ALTER TABLE `t_post_attach`   ADD COLUMN IF NOT EXISTS `real_name`		VARCHAR(128)	NOT NULL	COMMENT '첨부파일명(실제 저장된 파일명)'	AFTER `display_name`;
ALTER TABLE `t_post_attach`   ADD COLUMN IF NOT EXISTS `ext`			VARCHAR(32)		NOT NULL	COMMENT '확장자'					AFTER `real_name`;
ALTER TABLE `t_post_attach`   ADD COLUMN IF NOT EXISTS `file_size`		VARCHAR(32)		NOT NULL	COMMENT '파일 사이즈'				AFTER `ext`;
ALTER TABLE `t_post_attach`   ADD COLUMN IF NOT EXISTS `save_path`		VARCHAR(256)	NOT NULL	COMMENT '파일 저장경로'				AFTER `file_size`;
ALTER TABLE `t_post_attach`   ADD COLUMN IF NOT EXISTS `type`			VARCHAR(128)	NOT NULL	COMMENT '파일 타입'				AFTER `save_path`;

CREATE TABLE IF NOT EXISTS `t_post_comment` (
    `post_comment_seq`		BIGINT(10)		UNSIGNED    NOT NULL AUTO_INCREMENT                                         COMMENT '게시물 덧글 시퀀스'
    , `post_seq`			BIGINT(10)		UNSIGNED	NOT	NULL 				                                        COMMENT '게시물 시퀀스'
    , `comp_seq`			BIGINT(10)		UNSIGNED	NOT	NULL 				                                        COMMENT '회사 시퀀스'
    , `user_seq`			BIGINT(10)		UNSIGNED	NOT	NULL 				                                        COMMENT '사용자 시퀀스'
    , `create_seq`	        BIGINT(10)		UNSIGNED	NOT NULL DEFAULT CURRENT_TIMESTAMP								COMMENT '생성자'
    , `create_dt`	        TIMESTAMP					NOT NULL DEFAULT CURRENT_TIMESTAMP								COMMENT '생성일시'
    , `modify_seq`	        BIGINT(10)		UNSIGNED	NOT NULL DEFAULT CURRENT_TIMESTAMP								COMMENT '수정자'
    , `modify_dt`	        TIMESTAMP					NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP	COMMENT '수정일시'
    , PRIMARY KEY	(`post_comment_seq`)
    , KEY			(`post_seq`)
    , KEY			(`comp_seq`)
    , KEY			(`user_seq`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COMMENT='게시물 덧글';
ALTER TABLE `t_post_comment`   ADD COLUMN IF NOT EXISTS `comment`	MEDIUMTEXT	NOT NULL	COMMENT '덧글'	AFTER `user_seq`;
ALTER TABLE `t_post_comment`   ADD COLUMN IF NOT EXISTS `del_yn`	CHAR(1)	NOT NULL    COMMENT '삭제여부'    	AFTER `comment`;











