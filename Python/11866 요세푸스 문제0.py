from collections import deque

N, K = map(int, input().split())
number = deque([i for i in range(1, N + 1)])
result = "<"

for i in range(N):
    for j in range(K - 1):
        number.append(number.popleft())
    result += str(number.popleft()) + ", "

print(result[:-2] + ">")    