package teste;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.sisfin.financeiro.bd.HibernateUtil;
import com.sisfin.financeiro.model.Pessoa;

public class TesteHibernate {
	
	
	public static void main(String[] args) {
		Session session = HibernateUtil.getSession();
		
		@SuppressWarnings("unchecked")
		List<Pessoa> pessoas = session.createCriteria(Pessoa.class).add(Restrictions.gt("codigo", 3)).list();
		
		for(Pessoa p :pessoas){
			
			System.out.println(p.getCodigo() + " - " + p.getNome());
			
		}
		
		
		
		session.close();
	}

}
