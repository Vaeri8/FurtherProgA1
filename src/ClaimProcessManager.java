import java.util.Iterator;
import java.util.List;

interface ClaimProcessManager {
    void add(Claim claim);

    void update(Claim claim);

    void delete(String claimId);

    Claim getOne(String claimId);
}