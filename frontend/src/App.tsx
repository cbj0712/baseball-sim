import { PlayerListPage } from './features/player/pages/PlayerListPage'
import { AppLayout } from './layouts/AppLayout';

function App() {
    return (
        <AppLayout>
            <section style={{
                borderRadius: 16,
                backgroundColor: '#ffffff',
                border: '1px solid #e5e7eb',
                padding: 16,
                boxShadow: '0 1px 2px rgba(15, 23, 42, 0.04), 0 1px 3px rgba(15, 23, 42, 0.08)'
            }}>
                <h1 style={{
                    margin: 0,
                    fontSize: 18,
                    fontWeight: 600
                }}> 
                    레이아웃 테스트 
                </h1>
                <p style={{
                    marginTop: 8,
                    fontSize: 14,
                    color: '#6b7280'
                }}>
                    여기까지는 AppLayout이 제대로 동작하는지만 확인하는 용도입니다
                </p>
            </section>
            <aside style={{
                borderRadius: 16,
                backgroundColor: '#ffffff',
                border: '1px solid #e5e7eb',
                padding: 16,
                fontSize: 14,
                boxShadow: '0 1px 2px rgba(15, 23, 42, 0.04), 0 1px 3px rgba(15, 23, 42, 0.08)'
            }}>
                오른쪽 칼럼 (사이드바) 자리입니다
            </aside>
        </AppLayout>
    );
}

export default App
