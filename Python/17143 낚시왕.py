from collections import deque
R, C, M = map(int, input().split()) # y, x, 상어 수
shark = deque()
maps = [[(M, 0)for _ in range(C+1)] for _ in range(R+1)]
for i in range(M):
    r, c, s, d, z = map(int, input().split())
    maps[r][c] = (i, z)
    if(d == 1 or d == 2):
        s %= 2 * (R - 1)
    else:
        s %= 2 * (C - 1)
    shark.append([r, c, s, d, z])
# 1 : 위
# 2 : 아래
# 3 : 오른쪽
# 4 : 왼쪽
# x,y범위상
move = [[0, 0], [0, -1], [0, 1], [1, 0], [-1, 0]]
direction = [0, 2, 1, 4, 3]
start, sh = 0, [True] * (M+1)
def move_shark():
    lst = []
    maps = [[(M, 0)for _ in range(C+1)] for _ in range(R+1)]

    for j in range(M):
        if sh[j]:
            r, c, s, d, z = shark[j]
            for i in range(s):
                c += move[d][0] 
                r += move[d][1] 
                if not (1 <= r <= R and 1 <= c <= C):
                    d = direction[d]
                    c += 2 * move[d][0] 
                    r += 2 * move[d][1] 
            if maps[r][c][1] > z: # 큰 상어가 있는 경우
                sh[j] = False
                lst.append([r, c, s, d, z])
                continue
            else:
                sh[maps[r][c][0]] = False
                maps[r][c] = (j, z)
                lst.append([r, c, s, d, z])

        else:
            lst.append(shark[j])
    for i in range(M):
        shark[i] = lst[i]
    return maps

def get_shark(index):
    weight = 0
    for i in range(1, R+1):
        if maps[i][index][1] != 0:
            weight = maps[i][index][1]
            sh[maps[i][index][0]] = False
            break
    return weight

result = 0
for i in range(1, C+1):
    result += get_shark(i)
    maps = move_shark()
print(result)