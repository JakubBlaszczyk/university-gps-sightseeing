package history;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface HistoryRepository extends MongoRepository<History, Key> {

  @Query("{id: '?0'}")
  History findHistoryByMonumentId(Integer id);

  @Query("{id: '?0'}")
  History findHistoryByUserId(Integer id);

  public long count();

}
