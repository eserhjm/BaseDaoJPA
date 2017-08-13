public interface BaseDao<T> {
	T insert(T t);
	T update(T t);
 	T queryById(Object id);
	List<T> queryAll();
	List<T> findByExample(T example);
	boolean deleteByExample(T example);
	boolean deleteById(Object id);
	boolean deleteAll();
	boolean isExist(T t);
	boolean isExistById(Object id);
	long size();
	
	EntityManager getEm();
	void setEm(EntityManager em);
}
