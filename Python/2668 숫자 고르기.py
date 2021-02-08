import sys
from collections import deque

N = int(sys.stdin.readline())
number = [[i for i in range(N + 1)],[0 for _ in range(N + 1)]]
result = []
for i in range(N):
    number[1][i + 1] = int(sys.stdin.readline())

for i in range(1, N + 1):
    possible = False
    check = [False] * (N + 1)
    check[i] = True
    circle = deque([i])
    while(circle):
        move = circle.popleft()
        if(not check[number[1][move]]):
            circle.append(number[1][move])
            check[number[1][move]] = True
        else:
            if(number[1][move] == i):
                possible = True
                break
    count = 0
    temp = []
    if(possible):
        for i in range(1, N+1):
            if(check[i]):
                result.append(i)

result = set(sorted(result))
print(len(result))
for i in result:
    print(i)