# crscube
CRS Cube 코딩테스트용 레포지토리입니다.

## 1. Programing
**crscube** 폴더에 해당합니다.

## 2. RESTful API
**highschool** 폴더에 해당합니다.

### [POST] /auth/login

**req**
```json
{
  "username": "전민찬",
  "password": "qwer1234"
}
```

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

**req**
```json
```

