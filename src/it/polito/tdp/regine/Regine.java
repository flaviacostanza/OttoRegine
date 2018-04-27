package it.polito.tdp.regine;

import java.util.ArrayList;
import java.util.List;
/**
 * disposiione delle regine sulla scacchiera
 * @author Flavia
 *
 */
public class Regine {
	
	public List<Integer> posiziona(int N){
		
		ArrayList<Integer> soluzione= new ArrayList<Integer>();//è il parziale che passo al livello zero
		if(cerca(soluzione, N, 0)) {//appena arriva a soluzione restituisce true
			return soluzione;
		}else {
			return null;
		}
		
		
	}
	
	/**
	 * Assumendo che scacchiera sia piena nelle righe da 0 a liv-1 determina
	 * le possibili posizioni per la regina alla riga liv e prova ricorsivamente a posizionarla
	 * @param scacchiera lista delle posizioni delle regine precedenti
	 * @param N dimensione della scacchiera
	 * @param liv riga a cui deve lavorare la ricorsione
	 */
	private boolean cerca(List<Integer>scacchiera , int N, int liv ) {
		
		//CASO TERMINALE, soluzione trovata
		if(liv==N) {
			//System.out.println(scacchiera);
			return true;
		}
		
		//CASO INTERMEDIO
		//elementi possibili: tutti i numero da 0 a N-1
		for(int mossa=0; mossa<N; mossa++) {			
			if(posizioneSicura(scacchiera, liv, N, mossa)) {//se la mssa è sicura provo a farla
				scacchiera.add(mossa);
				if(cerca(scacchiera, N, liv+1))//se cerca ritorna true
					return true;//ritorno true senza fare backtrack
				scacchiera.remove(liv); 
			}
		}
		return false;
	}

	private boolean posizioneSicura(List<Integer> scacchiera, int liv, int n, int mossa) {
		//VERIFICA COLONNA
		for(int riga=0; riga<liv; riga++) {
			if(scacchiera.get(riga)==mossa)//se in qualche riha precedente è messa sulla stessa colonna non è sicura
				return false;
		
			//VERIFICA DIAGONALE DESTRA
			if(riga-scacchiera.get(riga) == liv-mossa)
				return false;
			//VERIFICA DIAGONALE SINISTRA
			if(riga+scacchiera.get(riga) == liv+mossa)
				return false;
			
		}
			return true;
	}
	
}

// la riga da controllare ha posizione [riga, scacchiera.get(riga)] 
//la riga da posizionare ha posizione [liv, mossa]
//condizione per cui si possono mangiare: differenza fra righe e colonne è uguale

//mangia su diagonale destra R1-C1== R2-C2
//mangia su diagonale sinistra R1+ C1 == R2 +C2