import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { SuivEnergiesTestModule } from '../../../test.module';
import { TD013InstalationECSDetailComponent } from 'app/entities/td-013-instalation-ecs/td-013-instalation-ecs-detail.component';
import { TD013InstalationECS } from 'app/shared/model/td-013-instalation-ecs.model';

describe('Component Tests', () => {
  describe('TD013InstalationECS Management Detail Component', () => {
    let comp: TD013InstalationECSDetailComponent;
    let fixture: ComponentFixture<TD013InstalationECSDetailComponent>;
    const route = ({ data: of({ tD013InstalationECS: new TD013InstalationECS(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SuivEnergiesTestModule],
        declarations: [TD013InstalationECSDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(TD013InstalationECSDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(TD013InstalationECSDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load tD013InstalationECS on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.tD013InstalationECS).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
