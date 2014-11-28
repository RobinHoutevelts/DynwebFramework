package domain;

public abstract class Identifiable {

    protected long id;

    public Identifiable() {
        id = 0;
    }

    public long getId() {
        return id;
    }

    protected void setId(long id) throws DomainException {
        if (id < 0) throw new DomainException("An id must be strict positive.");
        else this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Identifiable) return ((Identifiable)o).getId() == this.getId();
        return false;
    }
}