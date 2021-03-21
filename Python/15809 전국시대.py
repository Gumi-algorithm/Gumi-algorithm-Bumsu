import sys
input = sys.stdin.readline

def find(x):
    if(p[x][0] == x): return x
    p[x][0] = find(p[x][0]) 
    return p[x][0]

def union(cmd, x, y):
    x = find(x)
    y = find(y)
    if(cmd == 1):
        p[y][1] += p[x][1]
        p[x][0] = y
    else:
        if(p[x][1] < p[y][1]):
            x, y = y, x
        if(p[x][1] != p[y][1]):
            p[x][1] -= p[y][1]
            p[y][0] = x
        else:
            p[x][0] = p[y][0] = 0

N, M = map(int, input().split())

p = [[i, 0] for i in range(N + 1)]
for i in range(1, N + 1):
    p[i][1] = int(input())
for i in range(M):
    cmd, x, y = map(int, input().rstrip().split())
    union(cmd, x, y)
res = []
for i in range(1, N + 1):
    if(i == p[i][0] and p[i][1] != 0):
        res.append(p[i][1])
if(len(res) == 0):
    print(len(res))
else:
    res.sort()
    print(len(res))
    print(*res)