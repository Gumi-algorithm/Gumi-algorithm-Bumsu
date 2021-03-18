import sys
sys.setrecursionlimit(100000)
input = sys.stdin.readline

N, M = map(int, input().split())

head = [ i for i in range(N + 1)]
res = []

def setFind(a):
    if(head[a] == a):
        return a
    else:
        head[a] = setFind(head[a])
        return head[a]

def Union(a, b):
    head[setFind(a)] = head[setFind(b)]

for i in range(M):
    cmd, a, b = map(int, input().split())
    if(cmd == 0):
        Union(a, b)
    else:
        if(setFind(a) == setFind(b)):
            res.append("YES")
        else:
            res.append("NO")

print(head)
for i in res:
    print(i)