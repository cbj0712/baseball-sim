import styles from './LineupAndPitchingPanel.module.scss';

export function LineupAndPitchingPanel() {
    return (
        <section className={styles.panel}>
            <header className={styles.header}>
                <h2 className={styles.title}>라인업 / 투수 운용</h2>
                <p className={styles.subtitle}>타순, 선발 로테이션, 불펜 역할을 설정합니다</p>
            </header>

            <div className={styles.grid}>
                <div className={styles.block}>
                    <h3 className={styles.blockTitle}>타순</h3>
                    <p className={styles.placeholder}>타순 편집 UI 예정</p>
                </div>
                <div className={styles.block}>
                    <h3 className={styles.blockTitle}>선발 로테이션</h3>
                    <p className={styles.placeholder}>선발 로테이션 편집 UI 예정</p>
                </div>
                <div className={styles.block}>
                    <h3 className={styles.blockTitle}>불펜 역할</h3>
                    <p className={styles.placeholder}>불펜 역할 편집 UI 예정</p>
                </div>
            </div>
        </section>
    );
}