import styles from './ClubSummaryPanel.module.scss';
import { formatWonToEokMan } from '@/utils/currency';

interface ClubSummaryPanelProps {
    totalPlayers?: number;
    avgOverall?: number;
    payroll?: number;
}

export function ClubSummaryPanel({totalPlayers=3, avgOverall=84, payroll=10000000000}: ClubSummaryPanelProps) {
    return (
        <aside className={styles.panel}>
            <h2 className={styles.title}>구단 요약</h2>

            <div className={styles.stats}>
                <div className={styles.statRow}>
                    <span className={styles.statLabel}>선수단 연봉</span>
                    <span className={styles.statValuePrimary}>{formatWonToEokMan(payroll)}</span>
                </div>
                <div className={styles.statRow}>
                    <span className={styles.statLabel}>선수단 인원</span>
                    <span className={styles.statValue}>{totalPlayers}명</span>
                </div>
                <div className={styles.statRow}>
                    <span className={styles.statLabel}>평균 OVR</span>
                    <span className={styles.statValue}>{avgOverall}</span>
                </div>
            </div>

            <div className={styles.note}>
                <p className={styles.noteTitle}>프론트 메모</p>
                <p className={styles.noteBody}>
                    선발 로테이션은 안정적이지만 불펜 보강이 필요합니다. <br/>
                    FA 시장과 2군 유망주를 함께 검토하세요.
                </p>
            </div>
        </aside>
    );
}