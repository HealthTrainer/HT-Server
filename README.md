# Health Trainer-Application

![HT](https://s3.us-west-2.amazonaws.com/secure.notion-static.com/278eec28-5bf3-4e21-9182-6e1728fa13c9/Untitled.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Credential=AKIAT73L2G45EIPT3X45%2F20220307%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20220307T121319Z&X-Amz-Expires=86400&X-Amz-Signature=4ec3ca7c66c70353a02cd8376e4cf2444b61586447121f84921f83789621dd41&X-Amz-SignedHeaders=host&response-content-disposition=filename%20%3D%22Untitled.png%22&x-id=GetObject)

## Overview
This Application have planned your workout and achieved your goals.

<br>

## Feature
- Sign up and Sign in
- Record workout history in calendar
- View workout history
- View other people workout history
- Follow other people
- Form a group

<br>


## Built With
This project was built using these technologies.

- Spring boot
- JPA
- AWS
- Mysql
- ERwin
- IntelliJ

<br>


## Contributing
Please Don`t

<br>

## Authors
MinjeongKong <br>
pawoo0211

<br>

## File
```bash
├── main
│   ├── java
│   │   └── com
│   │       └── healthtrainer
│   │           └── htserver
│   │               ├── config
│   │               │   ├── CustomUserDetailService.java
│   │               │   ├── JwtAuthenticationFilter.java
│   │               │   ├── JwtAuthenticationProvider.java
│   │               │   ├── SecurityConfig.java
│   │               │   └── WebConfig.java
│   │               ├── domain
│   │               │   ├── calendar
│   │               │   │   ├── CalendarHistory.java
│   │               │   │   ├── CalendarHistoryRepository.java
│   │               │   │   ├── Calendar.java
│   │               │   │   └── CalendarRepository.java
│   │               │   ├── exercise
│   │               │   │   ├── ExerciseHistory.java
│   │               │   │   ├── ExerciseHistoryRepository.java
│   │               │   │   ├── ExerciseList.java
│   │               │   │   └── ExerciseListRepository.java
│   │               │   ├── Follow
│   │               │   │   ├── Follow.java
│   │               │   │   └── FollowRepository.java
│   │               │   ├── register
│   │               │   │   ├── User.java
│   │               │   │   └── UserRepository.java
│   │               │   └── team
│   │               │       ├── Team.java
│   │               │       ├── TeamRepository.java
│   │               │       ├── TeamUser.java
│   │               │       └── TeamUserRepository.java
│   │               ├── HtServerApplication.java
│   │               ├── service
│   │               │   ├── calendar
│   │               │   │   └── CalendarService.java
│   │               │   ├── exercise
│   │               │   │   ├── ExerciseHistoryService.java
│   │               │   │   └── ExerciseListService.java
│   │               │   ├── follow
│   │               │   │   ├── FollowService.java
│   │               │   │   └── SelectFollowService.java
│   │               │   ├── login
│   │               │   │   └── UserService.java
│   │               │   ├── register
│   │               │   │   └── RegisterService.java
│   │               │   ├── storage
│   │               │   │   ├── FileStorageService.java
│   │               │   │   └── StorageService.java
│   │               │   ├── team
│   │               │   │   └── TeamService.java
│   │               │   └── unfollow
│   │               │       └── UnFollowService.java
│   │               └── web
│   │                   ├── controller
│   │                   │   ├── calendar
│   │                   │   │   └── CalendarApiController.java
│   │                   │   ├── CustomErrorController.java
│   │                   │   ├── exercise
│   │                   │   │   ├── ExerciseHistoryApiController.java
│   │                   │   │   └── ExerciseListApiController.java
│   │                   │   ├── follow
│   │                   │   │   ├── FollowApiController.java
│   │                   │   │   └── SelectFollowApiController.java
│   │                   │   ├── login
│   │                   │   │   ├── LoginApiController.java
│   │                   │   │   └── UserApiController.java
│   │                   │   ├── register
│   │                   │   │   └── RegisterApiController.java
│   │                   │   ├── team
│   │                   │   │   ├── TeamApiController.java
│   │                   │   │   └── TeamMemberApiController.java
│   │                   │   └── unfollow
│   │                   │       └── UnFollowApiController.java
│   │                   └── dto
│   │                       ├── calendar
│   │                       │   ├── CalendarHistoryAllResponseDto.java
│   │                       │   ├── CalendarHistoryRequestDto.java
│   │                       │   ├── CalendarHistoryResponseDto.java
│   │                       │   ├── CalendarRequestDto.java
│   │                       │   └── CalendarTimeResponseDto.java
│   │                       ├── exercise
│   │                       │   ├── ExerciseDto.java
│   │                       │   ├── FindExerciseHistoryDto.java
│   │                       │   ├── FindExerciseListDto.java
│   │                       │   └── PutExerciseListRequestDto.java
│   │                       ├── follow
│   │                       │   ├── FollowDto.java
│   │                       │   └── SelectFollowResponseDto.java
│   │                       ├── login
│   │                       │   ├── LoginDto.java
│   │                       │   ├── UserCreateRequestDto.java
│   │                       │   ├── UserFindResponseDto.java
│   │                       │   ├── UserResponseDto.java
│   │                       │   └── UserUpdateRequestDto.java
│   │                       ├── register
│   │                       │   └── RegisterDto.java
│   │                       ├── ResponseDto.java
│   │                       └── team
│   │                           ├── AllTeamMemberHistoryResponseDto.java
│   │                           ├── CreateTeamRequestDto.java
│   │                           ├── JoinTeamRequestDto.java
│   │                           ├── MemberTimeResponseDto.java
│   │                           ├── SelectAllTeamMemberResponseDto.java
│   │                           ├── SelectTeamMemberResponseDto.java
│   │                           └── TeamResponseDto.java
│   └── resources
│       ├── application-prod.properties
│       └── application.properties
└── test
    └── java
        └── com
            └── healthtrainer
                └── htserver
                    ├── domain
                    │   └── register
                    │       └── RegisterTest.java
                    └── HtServerApplicationTests.java
```
