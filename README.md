[![Build Status](https://travis-ci.org/sriram-koushik/project-euler.svg?branch=master)](https://travis-ci.org/sriram-koushik/project-euler)[![code coverage](https://codecov.io/gh/sriram-koushik/project-euler/branch/master/graph/badge.svg)](https://codecov.io/gh/sriram-koushik/project-euler)

# Project-euler
Solutions to the problems in the Project Euler problem set (http://projecteuler.net/)

# Quick start
In order to get started and evaluate the solutions there are three approaches
1. Checkout the project and build locally with `gradle build` and run individual classes
2. Download the project, import in Eclipse and get it running
3. Download the files in https://github.com/sriram-koushik/project-euler/tree/master/src/main/java and run it locally

# Choosing problems

I had two approaches in choosing the problems. Honestly, this was the most difficult part of the exercise. The first approach I had was selecting problems randomly. I used the following code to randomly choose three problems:

```
import java.util.Random;
public class RandomProblem 
{
    public static void main(String arg[]) 
    {
        int max = 630, min = 10, count = 3;
        Random randObj = new Random();
        for(int index=0;index<count;index++)
            System.out.println(randObj.nextInt((max - min) + 1) + min);
    }
}
```
Then I figured a better approach would be to solve problems that might be relevant to the Adobe Document Cloud team. So with a combination of these two, have currently solved #10, #41 and #118. 

# Coding standards

1). Checkstyle: Have used Checkstyle (http://checkstyle.sourceforge.net/) to conform to adhere to an industry coding standard
2). SonarQube: Have used SonarQube as the static analyzer for the project to identify and fix code smells and vulnerabilities

# Build

This project uses Gradle as means to build the project

# Continuous Integration

This project has TravisCI configured which can build the project everytime a commit is made. This was necessary to run the other analysis scripts, test scripts automatically. We can always see the status of the build based on the latest commit here https://travis-ci.org/sriram-koushik/project-euler and in the badge [![Build Status](https://travis-ci.org/sriram-koushik/project-euler.svg?branch=master)](https://travis-ci.org/sriram-koushik/project-euler)


# Quality

Defect Management: 
Using Github issue management to track and fix defects in the system

Tests:
1). Unit tests
2). SonarQube static analyzer defects

Test automation:
The tests are currently run automatically everytime a commit is made. This is possible via JUnit, TravisCI and gradle scripts.

Quality metrics:
Have used http://codecov.io/ and https://www.eclemma.org/jacoco/ to automatically compute and display the code coverage and other relevant statistics, every commit. The code coverage results can be viewed at https://codecov.io/gh/sriram-koushik/project-euler or in the badge [![code coverage](https://codecov.io/gh/sriram-koushik/project-euler/branch/master/graph/badge.svg)](https://codecov.io/gh/sriram-koushik/project-euler)

# Configuration Management

Configuration Management is handled by Github branching structure. Since this is a very simple project, there are three types of branches:
master branch - Contains the most stable code
developer branch - Contains any basic miscellaneous changes, features
issue#x branch - This issue branch is used to fix a particular issue and then merged with developer upon verifying the test cases

# Estimation and Time Spent

The total time spent for the project was tracked via Toggl (http://toggl.com/).The list of tasks are defined below
| Task     | Estimated | Actual |
| ---      | ---       | ---    |
| Problem selection | 3h | 5h |
| Gradle setup | 1h | 1h |
| Travis CI setup  | 2h | 1h |
| JCoCo setup | 1h | 3h |


# Problem 1 - E10
<h2>Description</h2>
  Find the sum of all the primes below two million
  https://projecteuler.net/problem=10

<h2>Sample Output</h2>
  Output

<h2>Reasons for choosing this problem</h2> 

<h2>Approaches and analysis</h2> 

Analysed the efficient data structure which would encompass the two million numbers.
Implemented using the Sieve of Eratosthenes for the efficient performance complexity

<h2>Overall Time spent</h2>
__ minutes

# Problem 2 - E41
<h2>Description</h2>
Find the sum of all the primes below two million
https://projecteuler.net/problem=10

<h2>Sample Output</h2>
Output

<h2>Reasons for choosing this problem</h2> 

<h2>Approaches and analysis</h2> 

Analysed the efficient data structure which would encompass the two million numbers.
Implemented using the Sieve of Eratosthenes for the efficient performance complexity

<h2>Overall Time spent</h2>
__ minutes

# Problem 3 - E118
<h2>Description</h2>
Find the sum of all the primes below two million
https://projecteuler.net/problem=10

<h2>Sample Output</h2>
Output

<h2>Reasons for choosing this problem</h2> 

<h2>Approaches and analysis</h2> 

Analysed the efficient data structure which would encompass the two million numbers.
Implemented using the Sieve of Eratosthenes for the efficient performance complexity

<h2>Overall Time spent</h2>
__ minutes
