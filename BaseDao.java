public interface BaseDao<T> {
	T insert(T t);
	T update(T t);
 	List<T> queryAll();
	List<T> findByExample(T example);
	boolean deletByExample(T example);
	boolean isExist(T t);
}
