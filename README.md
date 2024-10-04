# Schedule Management API 명세서

## 1. 일정 생성 API
- **Method**: POST
- **URL**: `/api/schedules`
    - **Request**:
      ```json
      {
        "task": "할 일 내용",
        "author": "작성자 이름",
        "password": "비밀번호"
      }
      ```
    - **Response**:
      ```json
      {
        "id": 1,
        "task": "할 일 내용",
        "author": "작성자 이름",
        "createdAt": "2023-10-03T12:00:00",
        "updatedAt": "2023-10-03T12:00:00"
      }
      ```
    - **Status Code**: `201 Created`

---

## 2. 단건 일정 조회 API
- **Method**: GET
- **URL**: `/api/schedules/{id}`
    - **Path Variable**: `id` (조회할 일정의 고유 ID)
    - **Response**:
      ```json
      {
        "id": 1,
        "task": "할 일 내용",
        "author": "작성자 이름",
        "createdAt": "2023-10-03T12:00:00",
        "updatedAt": "2023-10-03T12:00:00"
      }
      ```
    - **Status Code**: `200 OK`

---

## 3. 전체 일정 목록 조회 API
- **Method**: GET
- **URL**: `/api/schedules`
    - **Response**:
      ```json
      [
        {
          "id": 1,
          "task": "첫 번째 할 일",
          "author": "작성자1",
          "createdAt": "2023-10-03T12:00:00",
          "updatedAt": "2023-10-03T12:00:00"
        },
        {
          "id": 2,
          "task": "두 번째 할 일",
          "author": "작성자2",
          "createdAt": "2023-10-03T13:00:00",
          "updatedAt": "2023-10-03T13:00:00"
        }
      ]
      ```
    - **Status Code**: `200 OK`

---

## 4. 일정 수정 API
- **Method**: PUT
- **URL**: `/api/schedules/{id}`
    - **Path Variable**: `id` (수정할 일정의 고유 ID)
    - **Request**:
      ```json
      {
        "task": "수정된 할 일 내용",
        "author": "작성자 이름",
        "password": "비밀번호"
      }
      ```
    - **Response**:
      ```json
      {
        "id": 1,
        "task": "수정된 할 일 내용",
        "author": "작성자 이름",
        "updatedAt": "2023-10-03T15:00:00"
      }
      ```
    - **Status Code**: `200 OK`

---

## 5. 일정 삭제 API
- **Method**: DELETE
- **URL**: `/api/schedules/{id}`
    - **Path Variable**: `id` (삭제할 일정의 고유 ID)
    - **Request**:
      ```json
      {
        "password": "비밀번호"
      }
      ```
    - **Status Code**: `200 OK`
