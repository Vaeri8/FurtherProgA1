//Luong Tuan Kiet - s3980288

import java.util.Iterator;
import java.util.List;

interface ClaimProcessManager {
    void add(Claim claim);
    void update(Claim claim);
    void delete(String claimId);
    Claim getOne(String claimId);
    List<Claim> getAll();

    Iterator<Claim> iterator();
}