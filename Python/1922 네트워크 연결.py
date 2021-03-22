import sys
input = sys.stdin.readline

def find(x):
    if(parent[x] == x): return x
    parent[x] = find(parent[x])
    return parent[x]

def union(x, y):
    x = find(x)
    y = find(y)
    if(x == y): return False
    parent[x] = y
    return True

N = int(input())
M = int(input())
parent = [i for i in range(N + 1)]
edge = [list(map(int, input().split())) for _ in range(M)]
edge.sort(key = lambda x: x[2])

cnt, res = 0, 0
for i in range(M):
    x, y, w = edge[i]
    if(union(x, y)):
        cnt += 1
        res += w
        if(cnt == N - 1):
            print(res)
            break
