public abstract class BaseDaoImpl<T> implements BaseDao<T>{
private Class<T> persistentClass;

	public BaseDaoImpl(Class<T> clz) {
		this.persistentClass = clz;
	}

	@PersistenceContext
	private EntityManager em;
	
	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}

	@Override
	public T insert(T t) {
		em.persist(t);
		return t;
	}

	@Override
	public T update(T t) {
		em.merge(t);
		return t;
	}

	@Override
	public T queryById(Object id) {
		return em.find(persistentClass, id);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<T> queryAll() {
	    return em.createQuery("Select t from " + persistentClass.getSimpleName() + " t").getResultList();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<T> findByExample(T example) {    
		System.out.println(example);
	    Session session = (Session) em.getDelegate();
	    Example ex = Example.create(example);
	    Criteria c = session.createCriteria(persistentClass).add(ex);
	    return c.list();
	}

	@Override
	public boolean deleteByExample(T example) {
		List<T> t=findByExample(example);
		for (T t2 : t) {
			em.remove(t2);
		}
		return true;
	}

	@Override
	public boolean deleteById(Object id) {
		try{
			T t = em.find(persistentClass, id);
			em.remove(t);
			return true;
		}catch(Exception e){
			return false;
		}
	}

	@Override
	public boolean deleteAll() {
		List<T> t=queryAll();
		for (T t2 : t) {
			em.remove(t2);;
		}
		return true;
	}
	
	@Override
	public boolean isExist(T t) {
		List<T> list=findByExample(t);
		if(list.size()>0){
			return true;
		}
		return false;
	}
	
	@Override
	public boolean isExistById(Object id) {
		T t=queryById(id);
		if(t!=null){
			return true;
		}
		return false;
	}

	@Override
	public long size() {
		return em.createQuery("Select t from " + persistentClass.getSimpleName() + " t").getResultList().size();
	}
}
