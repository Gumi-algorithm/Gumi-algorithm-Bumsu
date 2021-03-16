import sys
input = sys.stdin.readline

dy = [-1, 0, 1, 0]
dx = [0, 1, 0, -1]
# 북 동 남 서
result = []
for t in range(int(input())):
    n, r = map(int, input().split())
    arr = [[0 for _ in range(n + 2)] for _ in range(n + 2)]
    mir = [[] for _ in range(r)]
    for i in range(r):
        y, x = map(int, input().split())
        arr[y][x] = i + 2
    y, x = map(int, input().split())
    if(x == 0):
        d = 1
    elif(x == n + 1):
        d = 3
    elif(y == 0):
        d = 2
    elif(y == n + 1):
        d = 0
    
    possible = True
    while(possible):
        y += dy[d]
        x += dx[d]
        if(0 < y < n + 1 and 0 < x < n + 1):
            if(arr[y][x] > 1):
                index = arr[y][x] - 2
                for i in mir[index]:
                    if(i == d):
                        result.append([0, 0])
                        possible = False
                        break
                mir[index].append(d)
                d = (d + 1) % 4
        else:
            result.append([y, x])
            possible = False

for i in result:
    print(*i)