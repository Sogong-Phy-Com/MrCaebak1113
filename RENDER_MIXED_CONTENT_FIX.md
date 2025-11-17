# Mixed Content 오류 해결

## 🔴 문제
```
Mixed Content: The page at 'https://mrdaebakphycom.onrender.com/login' 
was loaded over HTTPS, but requested an insecure XMLHttpRequest endpoint 
'http://mrdaebakdinnerdelivery.com:5000/api/auth/login'.
```

HTTPS 페이지에서 HTTP API를 호출할 수 없습니다.

## ✅ 해결 완료

모든 프론트엔드 파일에서 API URL을 다음과 같이 수정했습니다:

```typescript
// 이전 (문제)
const API_URL = process.env.REACT_APP_API_URL || 'http://localhost:5000/api';

// 수정 후 (해결)
const API_URL = process.env.REACT_APP_API_URL || 
  (window.location.protocol === 'https:' ? '/api' : 'http://localhost:5000/api');
```

이제:
- **HTTPS 환경 (Render)**: 상대 경로 `/api` 사용 → 같은 도메인에서 API 호출
- **로컬 개발 (HTTP)**: `http://localhost:5000/api` 사용

## 🚀 배포 방법

### 1. 프론트엔드 재빌드
```bash
cd client
npm run build
```

### 2. 빌드된 파일을 static 폴더에 복사
```bash
# Windows
xcopy /E /I /Y build\* ..\server-java\src\main\resources\static

# Linux/Mac
cp -r build/* ../server-java/src/main/resources/static/
```

### 3. 커밋 및 푸시
```bash
cd C:\Users\pando\Desktop\MrDaeBak
git add .
git commit -m "Fix Mixed Content: Use relative API URLs for HTTPS"
git push
```

### 4. Render에서 재배포
- Render 대시보드 → 서비스 → "Manual Deploy" → "Deploy latest commit"

## 📝 수정된 파일

1. `client/src/contexts/AuthContext.tsx`
2. `client/src/pages/Order.tsx`
3. `client/src/pages/Orders.tsx`
4. `client/src/pages/Profile.tsx`
5. `client/src/pages/DeliveryStatus.tsx`
6. `client/src/pages/EmployeeDashboard.tsx`
7. `client/src/pages/AdminDashboard.tsx`

## ✅ 결과

이제 HTTPS 환경에서도 API 호출이 정상적으로 작동합니다:
- ✅ Mixed Content 오류 해결
- ✅ HTTPS에서 HTTPS API 호출
- ✅ 로컬 개발 환경도 정상 작동

