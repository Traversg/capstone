import axios from "axios";
import BindingClass from "../util/bindingClass";
import Authenticator from "./authenticator";

/**
 * Client to call the FiveLiftsService.
 *
 * This could be a great place to explore Mixins. Currently the client is being loaded multiple times on each page,
 * which we could avoid using inheritance or Mixins.
 * https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Classes#Mix-ins
 * https://javascript.info/mixins
  */
export default class FiveLiftsClient extends BindingClass {

    constructor(props = {}) {
        super();

        const methodsToBind = ['clientLoaded', 'getIdentity', 'login', 'logout', 'createProfile', 
        'getUpcomingWorkouts', 'getIsCurrentUser', 'getCurrentWorkout', 'addWorkout', 'getWorkoutHistory',
        'deleteUserProfileAndWorkoutHistory'];
        this.bindClassMethods(methodsToBind, this);

        this.authenticator = new Authenticator();;
        this.props = props;

        axios.defaults.baseURL = process.env.API_BASE_URL;
        this.axiosClient = axios;
        this.clientLoaded();
    }

    /**
     * Run any functions that are supposed to be called once the client has loaded successfully.
     */
    clientLoaded() {
        if (this.props.hasOwnProperty("onReady")) {
            this.props.onReady(this);
        }
    }

    /**
     * Get the identity of the current user
     * @param errorCallback (Optional) A function to execute if the call fails.
     * @returns The user information for the current user.
     */
    async getIdentity(errorCallback) {
        try {
            const isLoggedIn = await this.authenticator.isUserLoggedIn();

            if (!isLoggedIn) {
                return undefined;
            }

            return await this.authenticator.getCurrentUserInfo();
        } catch (error) {
            this.handleError(error, errorCallback)
        }
    }

    async login() {
        await this.authenticator.login();
    }

    async logout() {
        await this.authenticator.logout();
    }

    async isLoggedIn() {
        return await this.authenticator.isUserLoggedIn();
    }

    async getTokenOrThrow(unauthenticatedErrorMessage) {
        const isLoggedIn = await this.authenticator.isUserLoggedIn();
        if (!isLoggedIn) {
            throw new Error(unauthenticatedErrorMessage);
        }

        return await this.authenticator.getUserToken();
    }

    /**
     * Create a new profile owned by the current user.
     * @param squat the starting weight for the squat exercise.
     * @param benchPress the starting weight for the bench press exercise.
     * @param overheadPress the starting weight for the overhead press exercise.
     * @param barbellRow the starting weight for the barbell row exercise.
     * @param deadlift the starting weight for the deadlift exercise.
     * @param bodyWeight the body weight for the current user.
     * @param errorCallback (Optional) A function to execute if the call fails.
     * @returns The profile that has been created.
     */
    async createProfile(squat, benchPress, overheadPress, barbellRow, deadlift, bodyWeight, errorCallback) {
        try {
            const token = await this.getTokenOrThrow("Only authenticated users can create profiles.");
            const response = await this.axiosClient.post(`user`, {
                squat: squat,
                benchPress: benchPress,
                overheadPress: overheadPress,
                barbellRow: barbellRow,
                deadlift: deadlift,
                bodyWeight: bodyWeight
            }, {
                headers: {
                    Authorization: `Bearer ${token}`
                }
            });
            return response.data.profile;
        } catch (error) {
            this.handleError(error, errorCallback)
        }
    }

    /**
     * Gets upcoming workouts for the current user.
     * @param errorCallback (Optional) A function to execute if the call fails.
     * @returns The upcoming workouts retrieved.
     */
    async getUpcomingWorkouts(errorCallback) {
        try {
            const token = await this.getTokenOrThrow("Only authenticated users can retrieve workouts.");
            const response = await this.axiosClient.get('workouts', {
                headers: {
                    Authorization: `Bearer ${token}`
                }
            });
            return response.data.upcomingWorkouts;
        } catch (error) {
            this.handleError(error, errorCallback)
        }
    }

    /**
     * Gets current workout for the current user.
     * @param errorCallback (Optional) A function to execute if the call fails.
     * @returns The current workout retrieved.
     */
    async getCurrentWorkout(errorCallback) {
        try {
            const token = await this.getTokenOrThrow("Only authenticated users can retrieve workouts.");
            const response = await this.axiosClient.get('workouts?currentWorkout=true', {
                headers: {
                    Authorization: `Bearer ${token}`
                }
            });
            return response.data.upcomingWorkouts;
        } catch (error) {
            this.handleError(error, errorCallback)
        }
    }

    /**
     * Gets current or new status for the user.
     * @param errorCallback (Optional) A function to execute if the call fails.
     * @returns Status of user.
     */
    async getIsCurrentUser(errorCallback) {
        try {
            const token = await this.getTokenOrThrow("Only authenicated users can access user information.");
            const response = await this.axiosClient.get('users', {
                headers: {
                    Authorization: `Bearer ${token}`
                }
            });
            return response.data.isCurrentUser;
        } catch (error) {
            this.handleError(error, errorCallback);
        }
    }

    /**
     * Gets workout history for the user.
     * @param errorCallback (Optional) A function to execute if the call fails.
     * @returns workout history.
     */
        async getWorkoutHistory(errorCallback) {
            try {
                const token = await this.getTokenOrThrow("Only authenicated users can access user information.");
                const response = await this.axiosClient.get('workoutHistory', {
                    headers: {
                        Authorization: `Bearer ${token}`
                    }
                });
                return response.data.workoutHistory;
            } catch (error) {
                this.handleError(error, errorCallback)
            }
        }

     /**
     * Adds a completed workout owned by the current user.
     * @param workoutDate the workoutDate of the workout.
     * @param workoutType the workoutType of the workout.
     * @param timeStarted the time the workout started.
     * @param timeEnded the time the workout ended.
     * @param squatWeight the weight of each squat set in the workout.
     * @param benchPressWeight the weight of each bench press set in the workout.
     * @param overheadPressWeight the weight of each overhead press set in the workout.
     * @param barbellRowWeight the weight of each barbell row set in the workout.
     * @param deadliftWeight the weight of each deadlift set in the workout.
     * @param squatReps the amount of reps in each squat set in the workout.
     * @param benchPressReps the amount of reps in each bench press set in the workout.
     * @param overheadPressReps the amount of reps in each overhead press set in the workout.
     * @param barbellRowReps the amount of reps in each barbell row set in the workout.
     * @param deadliftReps the amount of reps in each deadlift set in the workout.
     * @param bodyWeight the body weight of the user measured during the workout.
     * @param errorCallback (Optional) A function to execute if the call fails.
     * @returns The workout that has been added.
     */
    async addWorkout(workoutDate, workoutType, timeStarted, timeEnded, squatWeight,
        benchPressWeight, overheadPressWeight, barbellRowWeight, deadliftWeight,
        squatReps, benchPressReps, overheadPressReps, barbellRowReps, deadliftReps,
        bodyWeight, errorCallback) {
        try {
            const token = await this.getTokenOrThrow("Only authenticated users can create profiles.");
            const response = await this.axiosClient.post(`workouts`, {
                workoutDate: workoutDate,
                workoutType: workoutType,
                timeStarted: timeStarted,
                timeEnded: timeEnded,
                squatWeight: squatWeight,
                benchPressWeight: benchPressWeight,
                overheadPressWeight: overheadPressWeight,
                barbellRowWeight: barbellRowWeight,
                deadliftWeight: deadliftWeight,
                squatReps: squatReps,
                benchPressReps: benchPressReps,
                overheadPressReps: overheadPressReps,
                barbellRowReps: barbellRowReps,
                deadliftReps: deadliftReps,
                bodyWeight: bodyWeight
            }, {
                headers: {
                    Authorization: `Bearer ${token}`
                }
            });
            return response.data.workout;
        } catch (error) {
            this.handleError(error, errorCallback)
        }
    }

    /**
     * Deletes current user and their workout history.
     * @param errorCallback (Optional) A function to execute if the call fails.
     * @returns true if deleted.
     */
    async deleteUserProfileAndWorkoutHistory(errorCallback) {
        try {
            const token = await this.getTokenOrThrow("Only authenicated users can access user information.");
            const response = await this.axiosClient.delete('resetProfile', {
                headers: {
                    Authorization: `Bearer ${token}`
                }
            });
            return response.data;
        } catch (error) {
            this.handleError(error, errorCallback);
        }
    }


    /**
     * Helper method to log the error and run any error functions.
     * @param error The error received from the server.
     * @param errorCallback (Optional) A function to execute if the call fails.
     */
    handleError(error, errorCallback) {
        console.error(error);

        const errorFromApi = error?.response?.data?.error_message;
        if (errorFromApi) {
            console.error(errorFromApi)
            error.message = errorFromApi;
        }

        if (errorCallback) {
            errorCallback(error);
        }
    }
}
