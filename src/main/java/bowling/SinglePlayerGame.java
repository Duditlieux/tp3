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
    
    private void nextLaunch() throws ErrTooMuchLaunch{
        if(m_round < MAX_ROUND-1){//tout les cas sauf le derniers round
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
                    //si on ne renverse pas tout, on force l'état du spare à false, pour ne pas que le prohain coup soit multiplié
                    m_isSpareState=false;
                    break;
                case 3://pas de strike, donc
                    m_strikeMultiplicator=1;
                    m_isStrikeState=false;
                    if(m_quillesRestantes == 0)
                        //spare
                        m_isSpareState=true;
                    else
                        //pas spare
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
        else{//cas du dernier round
            if(m_launch==3){//si on est à notre troisième lancé, on désactive tout les multiplicateurs
                m_isSpareState=false;
                m_isStrikeState=false;
            }
            if(m_launch!=1)
                if(--m_strikeMultiplicator <1)
                    //à partir du second lancé on décrémente le multiplicateur du strike
                    m_strikeMultiplicator=1;
            if(m_quillesRestantes == 0)
                m_quillesRestantes=MAX_QUILLES;
            //fin du lancé
            ++m_launch;
        }
        if(m_launch > 3)
            throw new ErrTooMuchLaunch();
    }
    
    /**
     * Cette méthode doit être appelée à chaque lancé de boule
     *
     * @param nombreDeQuillesAbattues le nombre de quilles abattues lors de
     * ce lancé
     */
    public void lancer(int nombreDeQuillesAbattues) throws ErrArgmuentExeption {
        if(nombreDeQuillesAbattues > m_quillesRestantes || nombreDeQuillesAbattues<0)
            throw new ErrArgmuentExeption("les quilles abattues est de " + nombreDeQuillesAbattues + ", ce qui est interdit");
        int scored;
        if(m_isStrikeState)//si le tire précédent était un strike, on lui applique un muslitplicateur
             scored=(nombreDeQuillesAbattues*m_strikeMultiplicator);
        else if(m_isSpareState)//si le lancé précédent était un spare, on multiplie par 2
            scored=(nombreDeQuillesAbattues*2);
        else//sinon on ajoute les points normalement
           scored=nombreDeQuillesAbattues;
        m_score+=scored;
        m_quillesRestantes-=nombreDeQuillesAbattues;
        //préparation au prochain lancé
        try {
            nextLaunch();
        } catch (Exception e) {
            e.printStackTrace();
        }
            
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
