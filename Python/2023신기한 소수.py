import sys
import math
N = int(sys.stdin.readline())

def isPrime(num):
    if num == 1:
        return False
    
    if num % 2 == 0:
        return num == 2

    for i in range(3, int(math.sqrt(num) + 1)):
        if(num % i == 0):
            return False
    return True

def dfs(index, num):
    if(index == N):
        print(num // 10)
        return
    for i in range(1, 10):
        num += i
        if isPrime(num):
            dfs(index + 1, num * 10)
        num -= i

dfs(0, 0)