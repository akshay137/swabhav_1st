package repository

// Repository base interface for repositories
type Repository interface {
	Get(uow *UnitOfWork, out interface{}, preloadAssociations []string) error
	GetAll(uow *UnitOfWork, out interface{}, preloadAsscoiations []string) error
	Add(uow *UnitOfWork, out interface{}) error
	Update(uow *UnitOfWork, out interface{}) error
	Delete(uow *UnitOfWork, out interface{}) error
}
