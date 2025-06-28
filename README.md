# crscube
CRS Cube 코딩테스트용 레포지토리입니다.

## 1. Programing
**crscube** 폴더에 해당합니다.

## 2. RESTful API
**highschool** 폴더에 해당합니다.

### [POST] /auth/login

**res**
```json
{
    "success": true,
    "message": "로그인 성공",
    "data": {
        "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiLsoITrr7zssKwiLCJyb2xlIjoiU1RVREVOVCIsImlhdCI6MTc1MTAzMzcyOCwiZXhwIjoxNzUxMDM3MzI4fQ.s8ZwnZ47dBkN6pmgyJIaCmIGcRo95TD2YH0m5qKg02s",
        "username": "전민찬",
        "role": "STUDENT"
    },
    "timestamp": "2025-06-27T23:15:28.158496"
}
```

### [POST] /students

**res**
```json
{
    "success": true,
    "message": "학생이 성공적으로 등록되었습니다.",
    "data": {
        "grade": 3,
      "classNumber": 2,
      "studentNumber": 16,
      "name": "전민찬",
      "birthday": "2007-05-01",
      "email": "chan2bo2@naver.com",
      "address": "대구광역시 00구 00동",
      "phoneNumber": "010-3762-3087",
      "createdAt": "2025-06-27T23:15:28.158496",
      "updatedAt": "2025-06-27T23:15:28.158496"
    },
    "timestamp": "2025-06-27T23:15:28.158496"
}
```

### [GET] /students

**res**
```json
{
    "success": true,
    "message": "학생 정보를 성공적으로 조회하였습니다.",
    "data": [{
      "grade": 3,
      "classNumber": 2,
      "studentNumber": 16,
      "name": "전민찬",
      "birthday": "2007-05-01",
      "email": "chan2bo2@naver.com",
      "address": "대구광역시 00구 00동",
      "phoneNumber": "010-3762-3087",
      "createdAt": "2025-06-27T23:15:28.158496",
      "updatedAt": "2025-06-27T23:15:28.158496"
    }],
    "timestamp": "2025-06-27T23:15:28.158496"
}
```

### [GET] /students/{id}

**res**
```json
{
    "success": true,
    "message": "학생 정보를 성공적으로 조회하였습니다.",
    "data": {
      "grade": 3,
      "classNumber": 2,
      "studentNumber": 16,
      "name": "전민찬",
      "birthday": "2007-05-01",
      "email": "chan2bo2@naver.com",
      "address": "대구광역시 00구 00동",
      "phoneNumber": "010-3762-3087",
      "createdAt": "2025-06-27T23:15:28.158496",
      "updatedAt": "2025-06-27T23:15:28.158496"
    },
    "timestamp": "2025-06-27T23:15:28.158496"
}
```

### [PATCH] /students/{id}

**res**
```json
{
    "success": true,
    "message": "학생 정보를 성공적으로 수정하였습니다.",
    "data": {
      "grade": 3,
      "classNumber": 2,
      "studentNumber": 16,
      "name": "전민돌",
      "birthday": "2007-05-01",
      "email": "chan2bo2@naver.com",
      "address": "대구광역시 00구 00동 1111번지",
      "phoneNumber": "010-3762-3087",
      "createdAt": "2025-06-27T23:15:28.158496",
      "updatedAt": "2025-06-27T23:15:28.158496"
    },
    "timestamp": "2025-06-27T23:15:28.158496"
}
```

### [DELETE] /students/{id}

**res**
```json
{
  "success": true,
  "message": "학생을 성공적으로 삭제하였습니다."
}
```

## 3. Design

### 1) SOLID (Open-Close Principal)
**설명**
교내 산학 프로젝트에서 백엔드 개발을 맡아 promotion과 user 부분을 담당했습니다.
이때 CustomException을 구현하기 위해 ExceptionCode라는 interface를 만들고, CustomExceptionCode class에 DI를 진행하여 CustomException을 진행하였습니다.
또한 ExceptionCode interface를 각 도메인별로 생기는 Exception에 대하여 대응하기 위해 도메인별 CustomException을 생성할때 또한 상속받아 사용하게 하였습니다.

**Pseudocode**
```java
// ExceptionCode.java
public interface ExceptionCode {

    int status();

    String exceptionName();

    String message();

}

//PromotionExceptionCode.java
@RequiredArgsConstructor
public enum PromotionExceptionCode implements ExceptionCode {

    PROMOTION_NOT_FOUND(404, "해당 상품을 찾을 수 없습니다.");

    private final int status;
    private final String message;

    @Override
    public int status() {
        return 0;
    }

    @Override
    public String exceptionName() {
        return "";
    }

    @Override
    public String message() {
        return "";
    }
}
```

### 2) OOP 3대 원칙 중 Encapsulation
**설명**
교내 산학 프로젝트에서 백엔드 개발을 할 당시에 UseCase, Service, Repository 3개의 계층으로 분리를 하여 캡술화를 진행하였습니다.
Service는 Repository를, UseCase는 Service를 참조하였습니다.
Repository는 DB 접근을 담당했고, Service는 비즈니스 로직을 처리하였습니다. 또 UseCase에서는 사용자 시나리오 단위의 작업 흐름을 정의하였습니다.

**Pseudocode**
```java
// UserRepository.java
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    Optional<UserEntity> findByUserId(String userId);
    boolean existsByUserId(String userId);
}

// BusinessRepository.java
public interface BusinessRepository extends JpaRepository<BusinessEntity, Long> {

    boolean existsByBusinessNumber(String businessNumber);
}

// UserService.java
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BusinessRepository businessRepository;

    public boolean existsByUserId(String userId) {
        return userRepository.existsByUserId(userId);
    }

    public boolean existsByBusinessNumber(String businessNumber){
        return businessRepository.existsByBusinessNumber(businessNumber);
    }

    public void signUp(UserEntity user) {
        if (existsByUserId(user.getUserId())) {
            throw new UserAlreadyExistsException();
        }
        userRepository.save(user);
    }

    public void signUp(BusinessEntity business){
        if (existsByBusinessNumber(business.getBusinessNumber())){
            throw new BusinessAlreadyExistsException();
        }
        businessRepository.save(business);
    }

    public UserEntity getByUserId(String userId) {
        return userRepository.findByUserId(userId)
                .orElseThrow(UserNotFoundException::new);
    }

}

//AuthUseCase.java
@Component
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class AuthUseCase {

    private final UserService userService;
    
    public ResponseData<UserEntity> login(SignInReq req, HttpSession session) {
        UserEntity user = userService.getByUserId(req.id());
        user.checkIfPasswordIsCorrect(Sha512PasswordEncoder.encode(req.password()));
        session.setAttribute("LOGIN_USER_ID", user.getUserId());
        return ResponseData.ok("로그인 성공", user);
    }

    public Response logout(HttpSession session) {
        session.invalidate();
        return Response.ok("로그아웃 성공");
    }

}
```

## 4. Software Architecture

### 1) 설계 방향
1. 비동기 작업은 PENDING, IN_PROGRESS, FAILED, COMPLETED의 상태를 가질 수 있습니다.
2. 작업 흐름
   1. 작업 생성 시 새 작업이 PENDING 상태로 queue 또는 DB에 등록됨
   2. EC2가 PENDING 상태인 task를 IN_PROGREESS로 전환 후 처리 시작
   3. if 실행 중인 task가 중단 된다면 -> 해당 작업은 IN_PROGRESS로 유지 but, 타임아웃이 될 시에 FAILED 혹은 PENDING상태로 변경 후 재시도 가능하게 함
   4. 작업 성공 시 COMPLETED로 상태 변경
   5. 작업 실패 시 FAILED나 PENDING 상태로 변경 후 재시도 가능하게 함
      
3. 시스템 설계
   1. 배포 시작
   2. EC2 Task에 할당된 IN_PROGRESS작업 확인
   3. Task 종료 전 작업 상태 업데이트 (변경X, 타임아웃으로 롤백 처리)
   4. 배포 완료 후 Task 재시작
   5. Watchdog이 중단된 작업을 찾고, 다시 PENDING상태로 변경 해 재시작
   

### 2) 도식화
```plain
 +-------------------+
 | 작업 생성 (PENDING) |
 +---------+---------+
           |
           v
 +---------------------+
 | ECS Task가 작업 수신  |
 +---------+-----------+
           |
           v
 +---------------------+
 | 작업 상태: IN_PROGRESS |
 +---------+-----------+
           |
   +-------+-------+
   |               |
   v               v
[완료 성공]      [Task 중단 or 실패]
   |               |
   v               v
[COMPLETED]   [FAILED 또는 PENDING으로 롤백]
                   |
                   v
              [재시도 큐에 재등록]
```


