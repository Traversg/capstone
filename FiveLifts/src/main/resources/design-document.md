# StrongLifts Clone Design (FiveLifts)

## 1. Problem Statement

As a weightlifter who uses the 5x5 program, I have trouble keeping track of all my workouts in my journal
and spend too much time calculating my current workout weights, warmup weights,
and deload weights. I would like to digitize all of my workouts that keeps track
of my progress and workouts , when I need to deload, and what my next workouts
will look like. I would also like the application to calculate my warmup weights,
deload weights, and rest time between sets.

## 2. Use Cases

U1. As a lifter, I want to be able to enter my starting weights, so I can begin my program.

U2. As a lifter, I want to see which workout I need to do that day, so I don't accidentally do the wrong workout.

U3. As a lifter, I want to see what my working weights are for the day, so I can set the appropriate weights on the bar.

U4. As a lifter, I want to enter how many reps I did for each set, so I can track my progress.

U5. As lifter, I want to be able to keep track of my body weight, so I can see if I'm gaining too much weight or not enough weight.

U6. As a lifter, I want to see my upcoming workouts calculated for the week, so I don't have to calculate each future workout.

U7. As a lifter, I want a timer to start after my set, so I know how long to rest between my sets.

U8. As a lifter, I want to see a recent history of my workouts, so I can see the progress I've made.

U9. As a lifter, I want to know how long my workout was, so I know how long to block out my workout in my calendar.

## 3. Stretch Goals

U1. As a lifter, I want to see a table of my progress for each lift.

U2. As a lifter, I want to see a graph of my progress for each lift.

U3. As a lifter, I want to see a full history of my workouts by date.

U4. As a lifter, I want to be able to customize my sets.

U5. As a lifter, I want to my deloads calculated for me when I don't hit 5x5 three workout days in a row.

U6. As a lifter, I want to see what my warmup weights are for each exercise.

## 4. Design UML

[class-diagram.puml](class-diagram.puml)

## 5. Tables

User Table:
- Email : Partition Key: String
- Body Weight : Attribute : double
- Deadlift : Attribute  : int
- Squat : Attribute : int
- Bench Press : Attribute : int
- Overhead Press : Attribute : int
- Barbell Row : Attribute : int

Workout Table:
- Email : Partition Key : String
- WorkoutDate : Sort Key : LocalDateTime
- WorkoutType : Attribute : enum
- TotalWorkoutTime : Attribute : Duration
- SquatWeight : Attribute : int
- BenchPressWeight : Attribute : int
- OverheadPressWeight : Attribute : int
- RowWeight : Attribute : int
- DeadliftWeight : Attribute : int
- SquatReps : Attribute : int[]
- BenchPressReps : Attribute : int[]
- OverheadPressReps : Attribute : int[]
- BarbellRowReps : Attribute : int[]
- DeadliftReps : Attribute : int[]

## 6. API

### 6.1 Add Workout Endpoint
- Accepts `POST` requests to /workouts
- Accepts data to add a new `workout` including the number of reps, the exercise, and the email associated with the workout. Returns the corresponding `workout`.

### 6.2 Get Upcoming Workouts Endpoint
- Accepts `GET` requests to /workouts
- Accepts email from AWS Cognito and returns the corresponding Workouts.

### 6.3 Create Profile Endpoint
- Accepts `POST` requests to /user
- Accepts data to create a new `user` with a provided email and starting weights and returns a new `user`.

![startWorkoutImage](diagram-13903889164156429315.png)
### 6.4 Is Current User Endpoint
- Accepts `GET` requests to /users
- Accepts email from AWS Cognito and returns a true if that user is in the user table, false if not.

### 6.5 Get Workout History Endpoint
- Accepts `GET` requests to /workoutHistory
- Accepts an email from AWS Cognito and returns the corresponding workouts.

### 6.6 Get Most Recent Workout Endpoint
- Accepts `GET` requests to /recentWorkout
- Accepts an email from AWS Cognito and returns the most recent workout.

### 6.7 Delete User Profile and Workouts Endpoint
- Accepts `DELETE` requests to /resetProfile
- Accepts an email from AWS Cognito and returns the email deleted and a boolean. 

## 7. Mockup

[Wireframe](https://www.figma.com/file/6ZHznRBsEo8gSSDoWypMTa/Capstone-Wireframe?node-id=3%3A200&t=90GwY0CNq8sfmf5y-1)