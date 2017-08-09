# BaseDao for JPA
### example
- You can use it like
```
BookDaoImpl.java

public class BookDaoImpl extends BaseDaoImpl<Book> implements IBookDao {

}
interface IBookDao extends BaseDao<Book>{

}
```
