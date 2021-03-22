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
    parent[x] = parent[y]
    return True

N, M = map(int, input().split())
parent = [i for i in range(N + 1)]
edge = [list(map(int, input().split())) for _ in range(M)]
edge.sort(key = lambda x: x[2])
cnt, res = 0, 0
for i in range(M):
    x, y, w = edge[i]
    if(union(x, y)):
        res += w
        cnt += 1
        if(cnt == N - 2):
            print(res)
            break