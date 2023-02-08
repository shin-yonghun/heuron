###[사용 기술]
####<환경>
- intellij
- spring boot 2.6.8
- java 8
- lombok
- jpa + mysql
####<api 인터페이스>
- swagger
####<테스트>
- junit5
- postman

###[문제해결]
####<응답 처리>
 응답은 기본적으로 code, message, payload 의 세 가지 요소를 갖습니다.\
 ex)처리 성공 시 -> code : 0, message : 'success' , payload : T\
 ex)NOT_FOUND 에러 시 -> code : 2, message : 'not found', payload : null
####<환자 정보 저장>
post로 환자 정보를 받아 db에 저장하고 환자 교유번호를 리턴합니다.\
성별과 질병여부의 도메인은 ENUM 으로 관리합니다.\
파라미터 바인딩시 @Valid를 체크합니다.\
params -> name(이름), age(나이), sex(성별), disease(질병여부)\
payload -> psn(환자고유번호)
####<환자 이미지 저장>
post로 환자 이미지와 환자고유번호를 받아 서버에 저장합니다.\
이미지는 UUID로 네이밍되어 src/main/resources/image/ 폴더에 저장됩니다.\
UUID는 회원정보와 같이 디비에 저장됩니다.\
이미 저장된 이미지가 있다면 기존이미지를 삭제하고 업로드 합니다.\
params -> psn(환자고유번호), img(이미지파일)\
payload -> String
####<환자 정보 조회>
get으로 환자고유번호를 받아 환자정보를 리턴합니다.\
환자가 없을 시, 이미지가 없을 시 exception이 떨어집니다.\
params -> psn(환자고유번호)\
payload -> name(이름), age(나이), sex(성별), disease(질병여부), img(이미지 URL)
####<환자 이미지 조회>
공통으로 사용하는 ResponseBase대신 ResponseEntity<Resource>에 이미지를 리턴합니다.\
이미지가 없다면 404 error로 리턴합니다.\
params -> 브라우저 상에서 이미지 URL 조회\
payload -> 이미지
####<환자 정보/이미지 삭제>
delete로 환자고유번호를 받아 해당 환자의 정보와 이미지를 물리삭제합니다.\
params -> psn(환자고유번호)\
payload -> String

###[DB 테이블 구조]

CREATE TABLE `patient_mt` (
`psn` int unsigned NOT NULL AUTO_INCREMENT COMMENT '환자 고유 번호',\
`name` varchar(10) NOT NULL COMMENT '이름',\
`age` smallint NOT NULL COMMENT '나이',\
`sex` tinyint NOT NULL COMMENT '성별',\
`disease` tinyint NOT NULL COMMENT '질병여부',\
`created_at` timestamp NOT NULL COMMENT '생성 일시',\
`img_nm` varchar(128) DEFAULT '' COMMENT '이미지이름',\
PRIMARY KEY (`psn`)\
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='환자정보'
