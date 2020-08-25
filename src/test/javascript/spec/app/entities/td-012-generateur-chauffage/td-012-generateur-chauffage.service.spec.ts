import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { TD012GenerateurChauffageService } from 'app/entities/td-012-generateur-chauffage/td-012-generateur-chauffage.service';
import { ITD012GenerateurChauffage, TD012GenerateurChauffage } from 'app/shared/model/td-012-generateur-chauffage.model';

describe('Service Tests', () => {
  describe('TD012GenerateurChauffage Service', () => {
    let injector: TestBed;
    let service: TD012GenerateurChauffageService;
    let httpMock: HttpTestingController;
    let elemDefault: ITD012GenerateurChauffage;
    let expectedResult: ITD012GenerateurChauffage | ITD012GenerateurChauffage[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(TD012GenerateurChauffageService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new TD012GenerateurChauffage(0, 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 0);
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a TD012GenerateurChauffage', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new TD012GenerateurChauffage()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a TD012GenerateurChauffage', () => {
        const returnedFromService = Object.assign(
          {
            systemeChauffageCogeneration: 'BBBBBB',
            td011InstallationChauffage: 'BBBBBB',
            tr004TypeEnergie: 'BBBBBB',
            consommationChauffage: 1,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of TD012GenerateurChauffage', () => {
        const returnedFromService = Object.assign(
          {
            systemeChauffageCogeneration: 'BBBBBB',
            td011InstallationChauffage: 'BBBBBB',
            tr004TypeEnergie: 'BBBBBB',
            consommationChauffage: 1,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a TD012GenerateurChauffage', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
