package com.testuser.user.repository;

import com.testuser.user.model.GrSub;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface SubGrRepository extends JpaRepository<GrSub,Long> {

    @Query(
            nativeQuery = true,
            value = "SELECT * FROM gr_sub WHERE id_gr = ?1"
    )
    List<GrSub> findAllByIdGr(int idGr);

    @Transactional
    @Modifying
    @Query(
            nativeQuery = true,
            value = " SELECT a.* , b.gr_code, b.gr_periode, b.tanggal, b.kunci , c.part_code , c.part_name, c.stock FROM gr_sub a LEFT JOIN gr b ON a.id_gr = b.id LEFT JOIN part_number c ON a.id_pn = c.id"
    )
    List<GrSub> findAllByJoinTable();

    @Transactional
    @Modifying
    @Query(
            nativeQuery = true,
            value = "DELETE FROM gr_sub WHERE id_gr = ?1"
    )
    void deleteAllByIdGr(int idGr);

}
