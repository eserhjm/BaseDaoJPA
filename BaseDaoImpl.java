public abstract class BaseDaoImpl<T> implements BaseDao<T>{
	private Class<T> persistentClass;

	@SuppressWarnings("unchecked")
	public BaseDaoImpl() {
		this.persistentClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

	@Autowired
	private EntityManager em;
	
	@Override
	public T insert(T t) {
		EntityTransaction tx=em.getTransaction();
		tx.begin();
		em.persist(t);
		tx.commit();
		return findByExample(t).get(0);
	}

	@Override
	public T update(T t) {
		EntityTransaction tx=em.getTransaction();
		tx.begin();
		em.merge(t);
		tx.commit();
		return findByExample(t).get(0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> queryAll() {
	    return em.createQuery("Select t from " + persistentClass.getSimpleName() + " t").getResultList();
	}
	 
	@Override
	public boolean isExist(T t) {
		List<T> list=findByExample(t);
		if(list.size()>0){
			return true;
		}
		return false;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<T> findByExample(T example) {    
		Class<T> entityClass =(Class<T>) example.getClass();
	    Session session = (Session) em.getDelegate();
	    Example ex = Example.create(example);
	    Criteria c = session.createCriteria(entityClass).add(ex);
	    return c.list();
	}

	@Override
	public boolean deletByExample(T example) {
		EntityTransaction tx=em.getTransaction();
		tx.begin();
		T t=findByExample(example).get(0);
		em.remove(t);
		tx.commit();
		return findByExample(example).size()==0;
	}
}
