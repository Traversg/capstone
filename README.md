[![LinkedIn][linkedin-shield]][linkedin-url]



<!-- PROJECT LOGO -->
<br />
<div align="center">
  <a href="https://github.com/Traversg/fivelifts">
    <img src="resources/logo.png" alt="Logo" width="80" height="80">
  </a>

<h3 align="center">FiveLifts</h3>

  <p align="center">
    Leave your workout log at home!
    <br />
    <a href="https://d1k3aqhoaq55zf.cloudfront.net/"><strong>Go the application »</strong></a>
    <br />
    <br />
    <a href="#usage">View Demo</a>
  </p>
</div>



<!-- TABLE OF CONTENTS -->
<details>
  <summary>Table of Contents</summary>
  <ol>
    <li>
      <a href="#about-the-project">About The Project</a>
      <ul>
        <li><a href="#built-with">Built With</a></li>
      </ul>
    </li>
    <li>
      <a href="#getting-started">Getting Started</a>
    </li>
    <li><a href="#usage">Usage</a></li>
    <li><a href="#contact">Contact</a></li>
  </ol>
</details>



<!-- ABOUT THE PROJECT -->
## About The Project

FiveLifts is a clone application of the StrongLifts that allows a user that use the 5x5 StrongLifts weightlifting program
to log their workouts, view their upcoming workouts, and track their progress over time.

FiveLifts was my NSS capstone  individual project.

Some requirements for this project included: 
- At least four API endpoints using the appropriate HTTP method for
each "CRUD" operation
- User authentication with Amazon Cognito supporting multiple users
- Deployment to AWS using Sam
- An AWS serverless stack that includes: API Gateway, CloudFormation, CloudFront, CloudWatch, Cognito, DynamoDB, and Lambda
- Language Tools/Frameworks including: Dagger, Gradle, Checkstyle, JUnit, Mockito, and PlantUML

### What I Learned

This project cemented most of the concepts I learned during our team midstone project. After developing a solid
understanding of creating API endpoints I was able to design, build, and debug the endpoints in far more efficient
manner. After building all of relevant classes in Java, I would then build unit/integration tests to test my code.
Once testing my code, I would test my endpoint locally with SAM in a Docker container with a cURLc command
to verify I was getting the expected result from the API. If I got the expected result, I would start to build out the
frontend in JavaScript and then test the endpoint from there. I found this way of building endpoints efficient to
creating the application in the time allotted and much easier to track down bugs.

Because I was able to build endpoints faster than before, I was able to spend more time exploring what I could do
with JavaScript and CSS - languages I was familiar with, but not really covered in our course. With my own research,
I learned how to make my application mobile friendly with scaled down elements and hamburger menus.

Amazon Cognito was also a new technology learned in this project. I learned how to sign up and login multiple users and
use tokens with their information to make requests to my API.

<p align="right">(<a href="#readme-top">back to top</a>)</p>

### Built With


* [![JavaScript][JavaScript]][JavaScript-url]
* [![CSS][CSS]][CSS-url]
* [![DynamoDB][DynamoDB]][DynamoDB-url]
* [![Lambda][Lambda]][Lambda-url]
* [![API Gateway][API Gateway]][Gateway-url]
* [![AWS CloudWatch][AWS CloudWatch]][CloudWatch-url]
* [![Java][Java]][Java-url]
* [![AWS CloudFormation][AWS CloudFormation]][CloudFormation-url]
* [![AWS CloudFront][AWS CloudFront]][CloudFront-url]
* [![AWS Cognito][AWS Cognito]][Cognito-url]

<p align="right">(<a href="#readme-top">back to top</a>)</p>


<!-- USAGE EXAMPLES -->
## Usage

[FiveLifts Demo](https://user-images.githubusercontent.com/57022409/220740931-4f994600-8919-4b41-85f2-3b8c97cd2191.mov)

<p align="right">(<a href="#readme-top">back to top</a>)</p>

<!-- CONTACT -->
## Contact

Travers Geoffray - traversgeoffray@gmail.com

Project Link: [https://github.com/Traversg/fivelifts](https://github.com/Traversg/fivelifts)

<p align="right">(<a href="#readme-top">back to top</a>)</p>

<!-- MARKDOWN LINKS & IMAGES -->
<!-- https://www.markdownguide.org/basic-syntax/#reference-style-links -->
[contributors-shield]: https://img.shields.io/github/contributors/Traversg/Party_Playlist.svg?style=for-the-badge
[contributors-url]: https://github.com/Traversg/Party_Playlist/graphs/contributors
[forks-shield]: https://img.shields.io/github/forks/github_username/repo_name.svg?style=for-the-badge
[forks-url]: https://github.com/github_username/repo_name/network/members
[stars-shield]: https://img.shields.io/github/stars/github_username/repo_name.svg?style=for-the-badge
[stars-url]: https://github.com/github_username/repo_name/stargazers
[issues-shield]: https://img.shields.io/github/issues/github_username/repo_name.svg?style=for-the-badge
[issues-url]: https://github.com/github_username/repo_name/issues
[license-shield]: https://img.shields.io/github/license/github_username/repo_name.svg?style=for-the-badge
[license-url]: https://github.com/github_username/repo_name/blob/master/LICENSE.txt
[linkedin-shield]: https://img.shields.io/badge/-LinkedIn-black.svg?style=for-the-badge&logo=linkedin&colorB=555
[linkedin-url]: https://www.linkedin.com/in/travers-geoffray/
[product-screenshot]: resources/screenshot.png
[DynamoDb]: https://img.shields.io/badge/AWS_DynamoDB-white?style=for-the-badge&logo=amazondynamodb&logoColor=4053D6
[DynamoDb-url]: https://aws.amazon.com/dynamodb/
[Lambda]: https://img.shields.io/badge/AWS_Lambda-lightblue?style=for-the-badge&logo=awslambda&logoColor=FF9900
[Lambda-url]: https://aws.amazon.com/lambda/
[API Gateway]: https://img.shields.io/badge/AWS_API_Gateway-pink?style=for-the-badge&logo=amazonapigateway&logoColor=FF4F8B
[Gateway-url]: https://aws.amazon.com/api-gateway/
[JavaScript]: https://img.shields.io/badge/JavaScript-20232A?style=for-the-badge&logo=javascript&logoColor=61DAFB
[JavaScript-url]: https://javascript.com/
[Bootstrap.com]: https://img.shields.io/badge/Bootstrap-563D7C?style=for-the-badge&logo=bootstrap&logoColor=white
[Bootstrap-url]: https://getbootstrap.com
[Java]: https://img.shields.io/badge/Java-darkgreen?style=for-the-badge
[Java-url]: https://java.com/
[AWS CloudFront]: https://img.shields.io/badge/AWS_CloudFront-orange?style=for-the-badge
[Cloudfront-url]: https://aws.amazon.com/cloudfront/
[AWS CloudFormation]: https://img.shields.io/badge/AWS_CloudFormation-red?style=for-the-badge
[Cloudformation-url]: https://aws.amazon.com/cloudformation/
[AWS CloudWatch]: https://img.shields.io/badge/AWS_CloudWatch-beige?style=for-the-badge&logo=amazoncloudwatch&logoColor=FF4F8B
[Cloudwatch-url]: https://aws.amazon.com/cloudwatch/
[AWS Cognito]: https://img.shields.io/badge/AWS_Cognito-darkred?style=for-the-badge
[Cognito-url]: https://aws.amazon.com/cognito/
[CSS]: https://img.shields.io/badge/CSS3-yellow?style=for-the-badge&logo=css3&logoColor=1572B6
[CSS-url]: https://www.w3.org/Style/CSS/Overview.en.html
