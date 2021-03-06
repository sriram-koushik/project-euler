[![Build Status](https://travis-ci.org/sriram-koushik/project-euler.svg?branch=master)](https://travis-ci.org/sriram-koushik/project-euler)[![code coverage](https://codecov.io/gh/sriram-koushik/project-euler/branch/master/graph/badge.svg)](https://codecov.io/gh/sriram-koushik/project-euler)

# Project-euler
Solutions to the problems in the Project Euler problem set (http://projecteuler.net/)

## Quick start
In order to get started and evaluate the solutions there are three approaches
1. Checkout the project and build locally with `gradle build` and run individual classes
2. Download the project, import in an IDE such as Eclipse as gradle project and get it running
3. Download the files in https://github.com/sriram-koushik/project-euler/tree/master/src/main/java and run it locally

## Choosing problems

I had two approaches while choosing the problems. Honestly, this was the most difficult part of the exercise. The first approach I had was selecting problems randomly. I used the following code to randomly choose three problems:

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
Then I figured a better approach would be to solve problems that might be relevant to the Adobe Document Cloud team. So with a combination of these two, chose to solve #10, #41 and #118. 

## Coding standards

1. Checkstyle: Used Checkstyle (http://checkstyle.sourceforge.net/) to conform to an industry coding standard which uses the Google Styling guide https://google.github.io/styleguide/javaguide.html

2. SonarQube: Used SonarQube (https://www.sonarqube.org/) as the static analyzer for the project to identify and fix code smells and vulnerabilities

## Build

This project uses Gradle (https://gradle.org/) as the means to build the project

## Continuous Integration

This project has TravisCI (https://travis-ci.org/) configured which can build the project everytime a commit is made. This was necessary to run the other analysis scripts, test scripts automatically. We can always see the status of the build based on the latest commit here https://travis-ci.org/sriram-koushik/project-euler and in the badge [![Build Status](https://travis-ci.org/sriram-koushik/project-euler.svg?branch=master)](https://travis-ci.org/sriram-koushik/project-euler)


## Quality

### Defect Management: 
Using Github issue management to track and fix defects in the system

### Tests:
1. <b>Unit tests:</b> Wrote unit tests in JUnit

2. <b>Static analysis:</b> Used SonarQube to run static analysis tests

### Test automation:
All tests are currently automated. The tests are run everytime a commit is made. This is possible via JUnit, TravisCI and gradle scripts.

### Coverage metrics:
Have used http://codecov.io/ and https://www.eclemma.org/jacoco/ to automatically compute and display the code coverage and other relevant statistics, every commit. The code coverage results can be viewed at https://codecov.io/gh/sriram-koushik/project-euler or in the badge [![code coverage](https://codecov.io/gh/sriram-koushik/project-euler/branch/master/graph/badge.svg)](https://codecov.io/gh/sriram-koushik/project-euler)

## Configuration Management

Configuration Management usually relates to version management, naming conventions and code branch guidelines. Since this is a simple project with only one contributor, we maintain two types of branches:  
master branch - Contains the most stable code
issue#x branch - This issue branch is used to fix a particular issue and then merged with developer upon verifying the test cases
A pull request is made to merge the issue branch to the master branch. This is to add traceability and an easy way to track issues.

## Estimation and Time Spent

The total time spent for the project was tracked via Toggl (http://toggl.com/) .The list of tasks are defined below

**Task**|**Estimated**|**Actual**
:-----:|:-----:|:-----:
Problem Selection| 3h| 4.5h
Gradle setup| 1h| 1h
Travis CI setup| 2h| 1h
JCoCo setup| 1h| 3h
E10 implementation| 2h| 1h
E10 test| 1h| 0.75h
E41 implementation| 3h| 1h
E41 test| 1h| 1.5h
E118 implementation| 2h| 1h
E118 test| 0.5h| 1h
Integeration| 1h| 1h
SonarQube Integration| 1h| 1h
Bug fixes| 5h| 3h
Readme Markdown writeup| 3h| 4h
Utilities test| 0h| 2h
**Total**|**26.5h**|**26.75h**

# Problems

## Euler Problem - 10
### Description
  Find the sum of all the primes below two million
  https://projecteuler.net/problem=10

### Sample Output</h2>
<img src="output/E10.PNG"/>

### Numerical answer 
142913828922

### Reasons for choosing this problem 
Use of prime numbers in Cryptography and encoding is well known. Several cloud services offer ways to encode data before moving files to the cloud and Adobe document cloud embraces the security ideals of Adobe (https://www.adobe.com/security.html). In order to achieve several use cases, one of the core building blocks in generating prime numbers and using them as a key. So building a library which generates prime numbers efficiently becomes very important. So via this problem, I felt it would be interesting to create a base utility class which can generate a large number of prime numbers readily available for use. The other added challenge was computing the sum until 2 million which was interesting. Overall, the ideas was to create a reusable library for serveral core functionalities which can be improved upon based on the need. 

### Approaches and analysis 

#### Naive approach:

1. Identify whether a particular number is prime by checking if it has factors until its square root (because any two factors when multiplied should not be greater than the number, hence enough to check until the square root).

2. Compute the sum for each of these prime numbers iteratively from 2 to 2000000.

#### Identification of challenges with scale and areas of improvement:

1. The number of prime numbers continuously grows exponentially with the range ```N/ln(N)```. Hence performing the naive approach these many times for every input costs time

2. Computing the sum everytime now includes finding the list of primes until that point which increases latency

3. There are several repeated computations in checking whether a number is prime. For example we know that 5 is prime. Now if we wanted to check if 625 is prime we need to check if numbers 2 to 25 are factors. We should not do this because we know that 5 times 25 is 625 and can never be a prime number.

#### Solving challenges:

1. Computing the list of primes until a given number in prior will mean that everytime the method to compute the sum is called, the list need not be recreated. This solves problems 1,2

2. Use Sieve of Eratosthenes (https://en.wikipedia.org/wiki/Sieve_of_Eratosthenes) to create a sieve of multiples in prior so that we don't repeat computations 


#### Current Approach:

1. Create a sieve based on the upper limit (2000000 in this case)

2. Create a list of prime numbers from the sieve

3. Compute the sum until any given limit (2000000 in this case)

#### Analysis:

**Factor**|**Naive**|**Current approach**
:-----:|:-----:|:-----:
| Time Complexity| ```O(Nlog(N))``` | ```O(Nlog(log(N)))```
| Space Complexity | ```O(N/log(N)``` | ```O(N)```
| Averge time to run | NA | 45ms

### Overall Time spent

**Task**|**Time Spent**
:-----:|:-----:
| Code | 0.5h
| Comments | 0.5h
| Test cases | 0.5h
| Fixing defects | 0.25h
**Total**|**1.75h**


## Euler Problem - 41
### Description
  Find the largest pan digital prime number possible
  https://projecteuler.net/problem=41

### Sample Output
<img src="output/E41.PNG"/>

### Numerical answer
7652413

### Reasons for choosing this problem 
We already discussed in Problem 10 about the importance of primes and the use of the same in the Document Cloud project. But an interesting class of prime numbers is pandigital primes. These are the primes which could generated by including all digits until the permissible length and are still prime. An example is 2143 which is 4 digit pandigital and is also prime. This class of primes is a cooler subset of primes and provides the same benefits as primes. Pandigital primes can be combined with other pandigital primes which can have n repetitions instead of 1 repetition

### Approaches and analysis
#### Naive approach:

For every number from 987654321 to 1 check if they are pandigital and if they are prime until we find one.

#### Identification of challenges with scale and areas of improvement:

1. In the worst case there are 987654321 numbers need to be checked if they are pandigital and if they are prime (though not true because the problem states 2143 exists)

2. Computing whether a number is prime still poses the same problems as Problem 10. 

3. The sieve size is extremely high (987654321) for checking whether a number is prime and will take a long time to create the sieve 

#### Solving challenges:

1. Generate the posible pandigital numbers and check if they are prime. This reduces the search space considerably. In the worst case the total number of values we need to consider are 9!+8!+7!+6!+5!+4!+3!+2!+1 which is 409113 and considerably lesser than 987654321

2. Instead of using the sieve, we should use the traditional approach of checking if they are factors until the square root to check if a number is prime

#### Current Approach:

Generate n digit pandigital combinations in non-increasing order and check if they are prime

#### Analysis:

**Factor**|**Naive**|**Current approach**
:-----:|:-----:|:-----:
| Time Complexity| ```O(Nlog(log(N)))``` | ```O(Klog(K))```
| Space Complexity | ```O(N)``` | ```O(K)```
| Averge time to run | 18920ms | 445ms

Here ```N``` is the largest possible number which is 987654321 and the maximum value is ```K``` is 9! = 362880 

### Overall Time spent

**Task**|**Time Spent**
:-----:|:-----:
| Code | 0.5h
| Comments | 0.5h
| Test cases | 1h
| Fixing defects | 0.5h
**Total**|**2.5h**

## Euler Problem - 118
### Description
  Find the total number of distinct sets containing each of the digits 1-9 exactly once contain only prime elements
  https://projecteuler.net/problem=118

### Sample Output
<img src="output/E118.PNG"/>

Numerical output: 44680

### Reasons for choosing this problem:
I was looking for a tougher problem with matching levels of relevancy and found problem 118 to be apt. With problems 10, 41, we were able to compute prime numbers and pandigital numbers. This problem focuses on the combinatorics part of how various sets with different subset lengths can be created with all elements in the set being prime. This is very useful in evaluating the strength of a key in an encryption system. For example, we know number of prime numbers under a value ``` K ``` to be approximately ``` N/ln(N) ```. So if a given number is a pandigital 10 digit number (whose total possible types is 9!), what's the probability and expected value (EV) of splitting them so that all splits are prime numbers. With this value, several factors of a number can be analyzed.

### Approaches and analysis

#### Naive approach:

1. Generate all prime numbers, combine them in all possible ways 

2. Check if each possible combination is pandigital

#### Identification of challenges with scale and areas of improvement:

1. The number of prime numbers until 987654321 is extremly high (47712769)

2. Combining these prime numbers in all possible ways will result in an extremely high number of combinations ``` 2^47712769 + 3^(47712769) + 4^(47712769)... ``` and so on. This can be reduced to avoid duplicates. But still the number is very high. Day to day servers with basic configuration can never handle this.

#### Solving challenges:

1. Instead of combining all prime numbers, we generate all combinations of 9 digit pandigital numbers and check if a valid split is possible

2. Since the number of splits posssible in a 9 digit number is only 8! (40320), that's a comparitively lesser number of subsets to check for

#### Current Approach:

Create combinations of 9 digit pandigital numbers and split them in all possible ways and check if all subsets are prime

#### Analysis:

**Factor**|**Current approach**
:-----:|:-----:
| Time Complexity| ```O(N!/(K!(n-k)!))```
| Space Complexity | ```O(1)```
| Averge time to run | 45ms

Here N refers to the size of the digits (9 in this case), K refers to the number of elements to pick (1 through 9). The N!/(K!(N-K)!) is the total possible number of combinations. Though this is done for varying number of K, it's a multiplier constant to the complexity where the constant is very small (<9)

### Overall Time spent

**Task**|**Time Spent**
:-----:|:-----:
| Code | 0.5h
| Comments | 0.5h
| Test cases | 0.5h
| Fixing defects | 0.5h
**Total**|**2.50h**

