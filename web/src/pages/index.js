import FiveLiftsClient from '../api/fiveLiftsClient';
import Header from '../components/header';
import BindingClass from '../util/bindingClass';
import DataStore from '../util/DataStore';

/**
 * Logic needed for the create playlist page of the website.
 */
class Index extends BindingClass {
    constructor() {
        super();
        this.bindClassMethods(['mount', 'login', 'redirectToWorkout', 'createProfile', 'redirectToCreateProfile'], this);
        this.dataStore = new DataStore();
        this.dataStore.addChangeListener(this.redirectToWorkout);
        this.dataStore.addChangeListener(this.redirectToCreateProfile);
        this.header = new Header(this.dataStore);
    }

    /**
     * Add the header to the page and load the FiveLiftsClient.
     */
    mount() {
        document.getElementById('login').addEventListener('click', this.login);
        document.getElementById('create-profile').addEventListener('click', this.createProfile)

        this.header.addHeaderToPage();

        this.client = new FiveLiftsClient();
    }

    /**
     * Method to run when the login button is pressed. Call the FiveLiftsService to login
     * the user.
     */
    async login() {
        document.getElementById('login').addEventListener('click', this.client.login);
        this.redirectToWorkout;
    }

    /**
     * When the user has logged in, redirect to their workout page.
     */
    async redirectToWorkout() {
        // TODO
        window.alert("Redirect to workout page.")
    }

    /**
     * Method to run when the create profile button is pressed. Call the FiveLiftsService to login
     * the user.
     */
    async createProfile() {
        this.client.login;
        this.redirectToCreateProfile;
    }

    /**
     * When the user has created a login information, redirect to create profile page.
     */
    async redirectToCreateProfile() {
        // TODO
        window.alert("Redirect to create profile page.")
    }
}

/**
 * Main method to run when the page contents have loaded.
 */
const main = async () => {
    const login = new Index();
    login.mount();
};

window.addEventListener('DOMContentLoaded', main);
