import styles from './AppHeader.module.scss';

export function AppHeader() {
    return (
        <header className={styles.header}>
            <div className={styles.inner}>
                <div className={styles.brand}>
                    <div className={styles.logo}>LSG</div>
                    <div className={styles.brandText}>
                        <span className={styles.productName}>LSG Baseball</span>
                        <span className={styles.productSub}> 야구 구단 운영 시뮬레이션</span>
                    </div>
                </div>   

                <nav className={styles.nav}>
                    <button className={`${styles.navItem} ${styles.navItemActive}`}> 선수 </button>
                    <button className={styles.navItem}>구단</button>
                    <button className={styles.navItem}>경기일정</button>
                    <button className={styles.navItem}>재정</button>
                </nav> 

                <div className={styles.profile}>
                    <span className={styles.seasonBadge}>2027 시즌 준비중</span>
                    <div className={styles.profileRight}>
                        <div className={styles.profileAvatar}>GM</div>
                        <div className={styles.profileName}>조병준 단장</div>
                    </div>
                </div>
            </div>
        </header>
    );
}