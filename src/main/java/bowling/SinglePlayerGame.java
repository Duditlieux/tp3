package bowling;

/**
 * Cette classe a pour but d'enregistrer le nombre de quilles abattues lors des
 * lancés successifs d'<b>un seul et même</b> joueur, et de calculer le score
 * final de ce joueur
 */
public class SinglePlayerGame {
    public final int MAX_ROUND = 10;
    public final int MAX_QUILLES = 10;
    /**
     * Constructeur
     */
    private int m_launch;
    private int m_round;
    private int m_score;
    private int m_quillesRestantes;
    private boolean m_isStrikeState;
    private boolean m_isSpareState;
    private int m_strikeMultiplicator;
    public SinglePlayerGame() {
        m_launch = 1;
        m_round = 1;
        m_score = 0;
        m_isStrikeState=false;
        m_isSpareState=false;
        m_quillesRestantes = MAX_QUILLES;
        m_strikeMultiplicator=1;
    }
    
    private void nextLaunch(){
        if(m_round < MAX_ROUND){//tout les cas sauf le derniers round
            switch(++m_launch){
                case 2:
                    if(m_quillesRestantes == 0){
                        //strike
                        if(++m_strikeMultiplicator>3)
                            m_strikeMultiplicator=3;
                        m_launch=1;
                        m_round++;
                        m_isStrikeState=true;
                        m_quillesRestantes=MAX_QUILLES;
                    }
                    m_isSpareState=false;
                    break;
                case 3:
                    m_strikeMultiplicator=1;
                    m_isStrikeState=false;
                    if(m_quillesRestantes == 0){
                        m_isSpareState=true;
                    }
                    else
                        m_isSpareState=false;
                    
                    //fin du round
                    m_launch=1;
                    m_round++;
                    m_quillesRestantes=MAX_QUILLES;
                    break;
                default:
                    break;
            }
        }
    }
    
    /**
     * Cette méthode doit être appelée à chaque lancé de boule
     *
     * @param nombreDeQuillesAbattues le nombre de quilles abattues lors de
     * ce lancé
     */
    public void lancer(int nombreDeQuillesAbattues) {
        int scored;
        if(m_isStrikeState)
             scored=(nombreDeQuillesAbattues*m_strikeMultiplicator);
        else if(m_isSpareState)
            scored=(nombreDeQuillesAbattues*2);
        else
           scored=nombreDeQuillesAbattues;
        System.out.println(scored);
        m_score+=scored;
        m_quillesRestantes-=nombreDeQuillesAbattues;
        nextLaunch();
    }

    /**
     * Cette méthode donne le score du joueur
     *
     * @return Le score du joueur
     */
    public int score() {
            return m_score;
    }
    
}
