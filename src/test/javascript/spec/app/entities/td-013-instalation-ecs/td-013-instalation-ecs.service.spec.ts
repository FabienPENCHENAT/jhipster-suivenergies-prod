import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { TD013InstalationECSService } from 'app/entities/td-013-instalation-ecs/td-013-instalation-ecs.service';
import { ITD013InstalationECS, TD013InstalationECS } from 'app/shared/model/td-013-instalation-ecs.model';

describe('Service Tests', () => {
  describe('TD013InstalationECS Service', () => {
    let injector: TestBed;
    let service: TD013InstalationECSService;
    let httpMock: HttpTestingController;
    let elemDefault: ITD013InstalationECS;
    let expectedResult: ITD013InstalationECS | ITD013InstalationECS[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(TD013InstalationECSService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new TD013InstalationECS(0, 'AAAAAAA', 0, 'AAAAAAA', 0);
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a TD013InstalationECS', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new TD013InstalationECS()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a TD013InstalationECS', () => {
        const returnedFromService = Object.assign(
          {
            tr005TypeInstallationEcs: 'BBBBBB',
            becs: 1,
            tv039FormuleBecs: 'BBBBBB',
            surfaceAlimentee: 1,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of TD013InstalationECS', () => {
        const returnedFromService = Object.assign(
          {
            tr005TypeInstallationEcs: 'BBBBBB',
            becs: 1,
            tv039FormuleBecs: 'BBBBBB',
            surfaceAlimentee: 1,
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

      it('should delete a TD013InstalationECS', () => {
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
