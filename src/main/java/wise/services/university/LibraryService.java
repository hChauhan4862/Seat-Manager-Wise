package wise.services.university;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import wise.common.CustomException;
import wise.entities.LibraryEntity;
import wise.repositories.university.LibraryRepo;
import wise.repositories.university.UniversityRepo;

@RequiredArgsConstructor
@Service
public class LibraryService {
    private final LibraryRepo libraryRepo;
    private final UniversityRepo universityRepo;

    // ---------------- List all libraries ----------------
    @Transactional(readOnly = true)
    public Page<LibraryEntity> getAllLibraries(Pageable pageable) {
        return libraryRepo.findAll(pageable);
    }

    // -------------- Get library by ID ----------------
    @Transactional(readOnly = true)
    public LibraryEntity getLibraryById(Integer id) {
        return libraryRepo.findById(id)
                .orElseThrow(() -> new CustomException("library.notFound", HttpStatus.NOT_FOUND));
    }

    // ---------------- Create a new library ----------------
    @Transactional
    public LibraryEntity createLibrary(LibraryEntity libraryEntity) {

        Integer maxId = libraryRepo.getMaxId();
        if (libraryRepo.existsByLibName(libraryEntity.getLibName())) {
            throw new CustomException("library.name.alreadyExists", HttpStatus.CONFLICT);
        }
        if(!universityRepo.existsById(libraryEntity.getUnivCode())){
            throw new CustomException("university.code.notExist", HttpStatus.BAD_REQUEST);
        }
        libraryEntity.setLibCode(maxId);

        return libraryRepo.save(libraryEntity);
    }

    // ---------------- Update a library by ID ----------------
    @Transactional
    public LibraryEntity updateLibrary(LibraryEntity libraryEntity) {
        if (libraryRepo.existsByLibNameAndLibCodeNot(libraryEntity.getLibName(), libraryEntity.getLibCode())) {
            throw new CustomException("library.name.alreadyExistsForAnotherId", HttpStatus.CONFLICT);
        }
        if(!universityRepo.existsById(libraryEntity.getUnivCode())){
            throw new CustomException("university.code.notExist", HttpStatus.BAD_REQUEST);
        }
        return libraryRepo.save(libraryEntity);
    }

    // ---------------- Delete a library by ID ----------------
    @Transactional
    public boolean deleteLibrary(Integer id) {
        if (!libraryRepo.existsById(id)) {
            throw new CustomException("library.notFound", HttpStatus.NOT_FOUND);
        }
        libraryRepo.deleteById(id);
        return true;
    }
}
