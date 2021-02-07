N, M = map(int, input().split())
staff = list(map(int, input().split()))
start, end = 0, max(staff) * M
time = end
while(start <= end):
    cnt = 0
    mid = (start + end) // 2
    for i in range(N):
        cnt += mid // staff[i]
    if(cnt >= M):
        time = min(mid, time)
        end = mid - 1
    else:
        start = mid + 1

print(time)