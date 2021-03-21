import sys
input = sys.stdin.readline

def find(x):
    if(p[x] == x): return x
    p[x] = find(p[x])
    return p[x]

def union(x, y):
    x = find(x)
    y = find(y)
    if(x == y):
        return False
    p[x] = p[y]
    return True
V, E = map(int, input().split())
edge = [ list(map(int,input().split())) for _ in range(E)]
edge.sort(key=lambda x: x[2])

cnt = 0
res = 0
p = [i for i in range(V + 1)]
for i in range(E):
    x, y, w = edge[i]
    if(union(x, y)):
        cnt += 1
        res += w
        if(cnt == V - 1):
            print(res)
            break