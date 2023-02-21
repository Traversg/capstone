import { DEFAULT_PRIMARY_KEY_VALUE_SEPARATOR } from '@aws-amplify/datastore/lib-esm/util';
import FiveLiftsClient from '../api/fiveLiftsClient';
import BindingClass from "../util/bindingClass";

/**
 * The header component for the website.
 */
export default class Header extends BindingClass {
    constructor() {
        super();

        const methodsToBind = [
            'addHeaderToPage', 'createSiteTitle', 'displayMobileMenu', 'displayProgressMenu'];
        this.bindClassMethods(methodsToBind, this);

        this.client = new FiveLiftsClient();
    }

    /**
     * Add the header to the page.
     */
    async addHeaderToPage() {
        const siteTitle = this.createSiteTitle();
        const currentPage = window.location.href;

        const header = document.getElementById('header');
        header.appendChild(siteTitle);
        const navBar = document.createElement('nav');
        navBar.innerHTML = `
        <div class="fullNav">
        <ul>
            <li class="dropdown">
                <button class="dropbtn">Progress</button>
                <div class="dropdown-content">
                    <a href="/progress/squatProgress.html">Squat</a>
                    <a href="/progress/benchPressProgress.html">Bench Press</a>
                    <a href="/progress/barbellRowProgress.html">Barbell Row</a>
                    <a href="/progress/overheadPressProgress.html">Overhead Press</a>
                    <a href="/progress/deadliftProgress.html">Deadlift</a>
                </div>
            </li>    
            <li><a href="/upcomingWorkouts.html">Upcoming Workouts</a></li>
            <li><a href="/workoutHistory.html">Workout History</a></li>
            <li><button class="logoutButton" id="logout" type="button">Logout</button></li>
        </ul>
        </div>
        <div class="hamburgerNav">
            <button type="button" class="hamburgerButton" id="hamburgerButton">&#9776</button>
            <div class="closeDiv hidden" id="closeDiv">
                <a class="close" id="close" href=${currentPage}>x</a>
            </div>
        </div>
        `;
        header.appendChild(navBar);

        document.getElementById('logout').addEventListener('click', this.client.logout);
        document.getElementById('hamburgerButton').addEventListener('click', this.displayMobileMenu);
    }

    createSiteTitle() {
        const homeButton = document.createElement('a');
        homeButton.classList.add('header_home');
        homeButton.href = 'index.html';
        homeButton.innerText = 'FIVELIFTS';

        const siteTitle = document.createElement('div');
        siteTitle.classList.add('site-title');
        siteTitle.appendChild(homeButton);

        return siteTitle;
    }

    displayMobileMenu() {
        const hamburgerButton = document.getElementById('hamburgerButton');
        hamburgerButton.classList.add('hidden');
        const closeDiv = document.getElementById('closeDiv');
        closeDiv.classList.remove('hidden');
        document.getElementById('background').style.backgroundColor = "#ff0024";
        document.getElementById('mobileMenuCard').innerHTML = `
        <div class="mobileMenu" id="mobileMenu">
            <div class="linkWrapper" id="progressButtonWrapper">
                <button class="dropbtn" id="progressButton" type="button">Progress</button>
            </div>
            <div class="linkWrapper" id="upcomingWorkoutsWrapper">    
                <a href="/upcomingWorkouts.html">Upcoming Workouts</a>
            </div>
            <div class="linkWrapper" id="workoutHistoryWrapper">
                <a href="/workoutHistory.html">Workout History</a>
            </div>
            <button class="logoutButtonMobile" id="logoutMobile" type="button">Logout</button>
        </div>
        `;
        const progressButton = document.getElementById('progressButton');
        const logout = document.getElementById('logoutMobile');
        progressButton.addEventListener('click', this.displayProgressMenu);
        logout.addEventListener('click', this.client.logout);
    }

    displayProgressMenu() {
        console.log("successful click")
        const progressButton = document.getElementById('progressButtonWrapper');
        const upcomingWorkoutsWrapper = document.getElementById('upcomingWorkoutsWrapper');
        const workoutHistoryWrapper = document.getElementById('workoutHistoryWrapper');
        progressButton.classList.add('hidden');
        upcomingWorkoutsWrapper.classList.add('hidden');
        workoutHistoryWrapper.classList.add('hidden');
        document.getElementById('mobileMenuCard').innerHTML = `
        <div class="mobileMenu" id="mobileMenu">
            <a href="/progress/squatProgress.html">Squat</a>
            <a href="/progress/benchPressProgress.html">Bench Press</a>
            <a href="/progress/barbellRowProgress.html">Barbell Row</a>
            <a href="/progress/overheadPressProgress.html">Overhead Press</a>
            <a href="/progress/deadliftProgress.html">Deadlift</a>
            <button class="backButton" id="backButton" type="button">&#8592;</button>
        </div>
        `;
        const backButton = document.getElementById('backButton');
        backButton.addEventListener('click', this.displayMobileMenu);
    } 
}
