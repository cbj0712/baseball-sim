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
                    <ul className={styles.navList}>
                        <li>
                            <button className={`${styles.navItem} ${styles.navItemActive}`} type='button'> 선수 </button>
                        </li>
                        <li>
                            <button className={styles.navItem} type='button'>구단</button>
                        </li>
                        <li>
                            <button className={styles.navItem} type='button'>경기일정</button>
                        </li>
                        <li>
                            <button className={styles.navItem} type='button'>재정</button>
                        </li>
                    </ul>
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